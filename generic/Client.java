package generic;

public class Client {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<Integer, String>(1, "apple");
        Pair<Integer, String> p2 = new Pair<Integer, String>(2, "pear");
        boolean same = Util.compare(p1, p2);
        System.out.println(same);
        String str = Util.getMiddle("123", "result", "12");
        System.out.println(str);
    }
}
