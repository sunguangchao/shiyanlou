package generic;

public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static <T> T getMiddle(T... a){
        return a[a.length/2];
    }
}

