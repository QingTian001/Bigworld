package map.util;

import java.util.*;

public class RandomUtil {
    public static float randomFloat(Random random) {
        return random.nextFloat();
    }

    public static float randomFloat(Random random,float bound) {
        return random.nextFloat() * bound;
    }

    public static <T> T randomElement(Random random,List<T> array) {
        return array.get(random.nextInt(array.size()));
    }

    public static <T> List<T> randomMultipleElements(Random random,Collection<T> collection, int amount) {
        List<T> array = new ArrayList<>(collection);
        amount = Math.min(amount, array.size());
        List<T> ret = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int randomIdx = random.nextInt(array.size() - i - 1);
            ret.add(array.get(randomIdx));
            Collections.swap(array,randomIdx, array.size() - i - 1);
        }
        return ret;
    }

    public static int randomIndex(Random random,List<?> array) {
        return random.nextInt(array.size());
    }

    public static void shuffle(Random random,List<?> list){
        if(list.size() <= 1){
            return;
        }
        for(int i=0;i<list.size();++i){
            int rand = random.nextInt(list.size());
            Collections.swap(list,i,rand);
        }
    }
}
