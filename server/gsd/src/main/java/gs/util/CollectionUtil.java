package gs.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.w3c.dom.Element;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by zhang tingjian on 2018/11/21.
 */
public class CollectionUtil {


    public static <T> T getLast(List<T> arr) {
        return arr.get(arr.size() - 1);
    }

    public static <T> T getLast(T[] arr) {
        return arr[arr.length - 1];
    }

    public static List<Integer> toList(int[] arr){
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    public static <K, V> void replace(Map<K, V> dest, Map<K, V> src) {
        dest.clear();
        dest.putAll(src);
    }

    public static <K, V extends Number> void merge(Map<K, V> dest, Map<K, V> src) {
        src.forEach(((k, v) -> {
            if (dest.containsKey(k)) {
                dest.put(k, add(dest.get(k), v));
            } else {
                dest.put(k, v);
            }
        }));
    }

    public static <K, V extends Number> void merge(Map<K, V>dest, K k, V v){
        if(dest.containsKey(k)){
            dest.put(k, add(dest.get(k), v));
        } else {
            dest.put(k, v);
        }
    }

    @SuppressWarnings("unchecked")
    public static <N extends Number> N add(N n1, N n2) {
        if (n1 instanceof Long) {
            Long ret = Math.addExact((long)n1, (long)n2);
            return (N) ret;
        }
        if (n1 instanceof Integer) {
            Integer ret = Math.addExact((int)n1, (int)n2);
            return (N) ret;
        }
        if (n1 instanceof Float) {
            Float ret = (Float) n1 + (Float) n2;
            return (N) ret;
        }
        if (n1 instanceof Double) {
            Double ret = (Double) n1 + (Double) n2;
            return (N) ret;
        }
        return null;
    }

    public static boolean containsAll(List<Integer> list, int[] arrays) {
        for (int value : arrays) {
            if (!list.contains(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAny(List<Integer> list, int[] arrays) {
        for (int value : arrays) {
            if (list.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsCount(List<Integer> list, int[] arrays, int num) {
        int count = 0;
        for (int value : arrays) {
            if (list.contains(value)) {
                count += 1;
            }
        }
        return count >= num;
    }

    public static<T> boolean containsAny(T[] arr, T obj) {
        if (arr == null) return false;
        for(var e : arr) {
            if(e.equals(obj))
                return true;
        }
        return false;
    }


    /**
     * 获得一个不重复的随机序列.比如要从20个数中随机出6个不同的数字,可以先把20个数存入数组中,然后调用该方法.注意方法调用完后totals
     * 里面的elements的顺序是变了的.适用于从一部分数中找出其中的绝大部分
     * @param totals
     *            所有数据存放在数组中
     * @param dest
     *            要返回的序列的长度
     * @return 生成的序列以数组的形式返回
     */
    public static int[] getRandomArray(int[] totals, int dest) {

        if (dest <= 0) {
            throw new IllegalArgumentException();
        }
        if (dest > totals.length) {
            return totals;
        }
        int[] ranArr = new int[dest];
        for (int i = 0; i < dest; i++) {
            // 得到一个位置
            int j = ThreadLocalRandom.current().nextInt(totals.length - i);
            ranArr[i] = totals[j];
            // 将未用的数字放到已经被取走的位置中,这样保证不会重复
            totals[j] = totals[totals.length - 1 - i];
        }
        return ranArr;
    }

    /**
     * 从一个集合中，随机选取几个，构成一个新的List
     *
     * @param collection 源集合
     * @param num 随机选取的个数
     * @return
     */
    public static <T> List<T> getRandomList(Collection<T> collection, int num) {
        //如果集合小于需要的个数，则直接返回
        if (collection.size() <= num) {
            List<T> result = new LinkedList<T>();
            result.addAll(collection);
            return result;
        }

        int[] tmpArray = new int[collection.size()];
        for (int i = 0; i < tmpArray.length; i++) {
            tmpArray[i] = i;
        }
        int[] chosen = getRandomArray(tmpArray, num);
        Arrays.sort(chosen);
        List<T> result = new LinkedList<T>();
        int i = 0;
        int j = 0;
        for (T t : collection) {
            if (chosen[j] == i) {
                result.add(t);
                j++;
                if (j >= chosen.length) {
                    break;
                }
            }
            i++;
        }
        return result;
    }

    public static String[] splitAttr(Element data, String key) {
        var attr = data.getAttribute(key);
        return attr.isEmpty() ? new String[0] : attr.split("[;:|,]");
    }

    public static String[] splitAttr(JsonElement data) {
        return data == null ? new String[0] : data.getAsString().split("[;:|,]");
    }
}
