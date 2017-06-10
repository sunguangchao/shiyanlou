package singleton;

/**
 * Created by 11981 on 2017/6/10.
 * 饿汉模式
 * 当类被加载的时候就会创建唯一的实例
 */
public class Singleton {
    //将构造方法私有化，不允许从外部直接创建对象
    private Singleton(){

    }
    //创建类的唯一实例
    private static Singleton instance = new Singleton();

    //提供一个用于获取实例的方法
    public static Singleton getInstance(){
        return instance;
    }

}
