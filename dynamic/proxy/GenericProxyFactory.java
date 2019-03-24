package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/20 5:07 PM
 * 通用的限制适配器工厂类
 */
public class GenericProxyFactory {
    public static <T> T getProxy(Class<T> inf, final T obj){
        return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class[]{inf}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(obj, args);
            }
        });
    }


}
