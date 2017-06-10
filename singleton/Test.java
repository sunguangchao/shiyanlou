package singleton;

/**
 * Created by 11981 on 2017/6/10.
 */
public class Test {
    public static void main(String[] args){
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        if (s1 == s2){
            System.out.println("s1与s2是同一个实例");
        }else{
            System.out.println("s1与s2不是同一个实例");
        }

        Singleton2 s3 = Singleton2.getInstance();
        Singleton2 s4 = Singleton2.getInstance();
        if (s3 == s4){
            System.out.println("s3与s4是同一个实例");
        }else{
            System.out.println("s3与s4不是同一个实例");
        }
    }
}
