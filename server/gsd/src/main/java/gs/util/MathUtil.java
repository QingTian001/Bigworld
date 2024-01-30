package gs.util;

import it.unimi.dsi.fastutil.HashCommon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.math.BigDecimal.ROUND_DOWN;

/**
 * Created by zhang tingjian on 2018/11/1.
 */
public class MathUtil {
    private static Random random() {
        return ThreadLocalRandom.current();
    }

    public static long randomLong() {
        return random().nextLong();
    }

    public static float randomFloat() {
        return random().nextFloat();
    }

    private static float randomFloat(float bound) {
        return random().nextFloat() * bound;
    }

    public static float randomFloat(float min, float max) {
        return randomFloat(max - min) + min;
    }

    public static int randomInt(int bound) {
        if (bound == 0) {
            return bound;
        }
        return random().nextInt(bound);
    }

    public static int randomInt(int min, int max) {
        return randomInt(max - min) + min;
    }

    public static <T> T random(T[] ts) {
        return ts[randomInt(ts.length)];
    }

    public static int random(int[] ints) {
        return ints[randomInt(ints.length)];
    }

    public static int clamp(int value, int min, int max) {
        return value < min ? min : (Math.min(value, max));
    }

    public static float clamp(float value, float min, float max) {
        return value < min ? min : (Math.min(value, max));
    }

    public static int randomByWeight(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum = sum + weight;
        }
        int random = MathUtil.randomInt(sum);
        int tmp = 0;
        for (int i = 0; i < weights.length; ++i) {
            tmp = tmp + weights[i];
            if (random < tmp)
                return i;
        }
        return weights.length - 1;
    }

    public static int randomByWeight(List<Integer> weights) {
        int sum = 0;
        for (int weight : weights) {
            sum = sum + weight;
        }
        int random = MathUtil.randomInt(sum);
        int tmp = 0;
        for (int i = 0; i < weights.size(); ++i) {
            tmp = tmp + weights.get(i);
            if (random < tmp)
                return i;
        }
        return weights.size() - 1;
    }

    public static <T> T randomByWeight(T[] array, ToIntFunction<T> elem2Weight) {
        int sum = 0;
        for (T weight : array) {
            sum = sum + elem2Weight.applyAsInt(weight);
        }

        int random = MathUtil.randomInt(sum);
        int tmp = 0;
        for (T elem : array) {
            tmp = tmp + elem2Weight.applyAsInt(elem);
            if (random < tmp) {
                return elem;
            }
        }
        return array[array.length - 1];
    }

    //去重随机
    //另一种更简洁但是可能开销更大的方法
    // https://stackoverflow.com/questions/2140787/select-k-random-elements-from-a-list-whose-elements-have-weights
    public static <T> Set<T> multipleRandomByWeight(List<T> array, ToIntFunction<T> elem2Weight, int count) {
        Set<T> ret = new HashSet<>();
        int sum = 0;
        for (T elem : array) {
            sum += elem2Weight.applyAsInt(elem);
        }
        count = Math.min(count, array.size());
        for (int i = 0; i < count; i++) {
            if (sum <= 0) {
                break;
            }
            int randomInt = randomInt(sum);
            int tmpSum = 0;
            for (T elem : array) {
                if (ret.contains(elem)) {
                    continue;
                }
                int weight = elem2Weight.applyAsInt(elem);
                tmpSum += weight;
                if (tmpSum > randomInt) {
                    ret.add(elem);
                    sum -= weight;
                    break;
                }
            }
        }
        return ret;
    }

    //去重随机
    public static <T> Set<T> multipleRandomByWeight(T[] array, ToIntFunction<T> elem2Weight, int count) {
        Set<T> ret = new HashSet<>();
        int sum = 0;
        for (T elem : array) {
            sum += elem2Weight.applyAsInt(elem);
        }
        count = Math.min(count, array.length);
        for (int i = 0; i < count; i++) {
            if (sum <= 0) {
                break;
            }
            int randomInt = randomInt(sum);
            int tmpSum = 0;
            for (T elem : array) {
                if (ret.contains(elem)) {
                    continue;
                }
                int weight = elem2Weight.applyAsInt(elem);
                tmpSum += weight;
                if (tmpSum > randomInt) {
                    ret.add(elem);
                    sum -= weight;
                    break;
                }
            }
        }
        return ret;
    }

    public static <T> T randomByWeight(List<T> array, ToIntFunction<T> elem2Weight) {
        int sum = 0;
        for (T weight : array) {
            sum = sum + elem2Weight.applyAsInt(weight);
        }

        int random = MathUtil.randomInt(sum);
        int tmp = 0;
        for (T elem : array) {
            tmp = tmp + elem2Weight.applyAsInt(elem);
            if (random < tmp) {
                return elem;
            }
        }
        return array.get(array.size()-1);
    }

    public static <T> T randomElement(List<T> array) {
        return array.get(randomInt(array.size()));
    }

    public static int nextPowerOfTwo(int num) {
        return HashCommon.nextPowerOfTwo(num);
    }

    public static long nextPowerOfTwo(long num) {
        return HashCommon.nextPowerOfTwo(num);
    }

    private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public static int hash(long h) {
        return hash(Long.hashCode(h));
    }

    //merge two integer-float maps, parameters will be left unchanged
    public static Map<Integer, Float> mergeIntFloatMap(Map<Integer, Float> map1, Map<Integer, Float> map2) {
        return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Float::sum));
    }

    private final static BigDecimal LONG_MAX = new BigDecimal(Long.MAX_VALUE);
    private final static BigDecimal INT_MAX = new BigDecimal(Integer.MAX_VALUE);

    public static long positiveMultipleExact(long origValue, float multiple) {
        if (origValue < 0) {
            throw new ArithmeticException("error origValue:" + origValue);
        }
        if (multiple < 0) {
            throw new ArithmeticException("error multiple:" + multiple);
        }

        BigDecimal origDecimal = new BigDecimal(origValue);
        BigDecimal mulDecimal = new BigDecimal(multiple);
        BigDecimal value = origDecimal.multiply(mulDecimal).setScale(0, RoundingMode.DOWN);

        if (value.compareTo(LONG_MAX) > 0) {
            throw new ArithmeticException("overflow");
        }

        return value.longValueExact();
    }

    public static int positiveMultipleExact(int origValue, float multiple) {
        if (origValue < 0) {
            throw new ArithmeticException("error origValue:" + origValue);
        }
        if (multiple < 0) {
            throw new ArithmeticException("error multiple:" + multiple);
        }

        BigDecimal origDecimal = new BigDecimal(origValue);
        BigDecimal mulDecimal = new BigDecimal(multiple);
        BigDecimal value = origDecimal.multiply(mulDecimal).setScale(0, RoundingMode.DOWN);

        if (value.compareTo(INT_MAX) > 0) {
            throw new ArithmeticException("overflow. " + value.toBigInteger().longValue());
        }

        return value.intValueExact();
    }

    public static void main(String[] args) {

        int v = 0x1fffffff;
        float m = 1.0000001f;

        long nanos = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            v = positiveMultipleExact(v, m);
        }

        System.out.println(System.nanoTime() - nanos);

    }
}
