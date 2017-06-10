package singleton;

/**
 * Created by 11981 on 2017/6/10.
 * 懒汉模式
 * 当类加载时不会创建实例，只有当获取时，才会开始创建实例
 */
public class Singleton2 {
    //将构造方法私有化，不允许从外部直接创建对象
    private Singleton2(){

    }
    //创建类的唯一实例
    private static Singleton2 instance;

    //提供一个用于获取实例的方法
    public static Singleton2 getInstance(){
        if (instance == null){
            instance = new Singleton2();
        }
        return instance;
    }

}
