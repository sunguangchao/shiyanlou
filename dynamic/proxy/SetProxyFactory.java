package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/20 4:52 PM
 */
public class SetProxyFactory {
    public static Set getSetProxy(final Set s){
//        return (Set) Proxy.newProxyInstance(
//                s.getClass().getClassLoader(), new Class[]{Set.class}, new LoggingInvocationHandler(s) {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return method.invoke(s, args);
//            }
//        });

        return (Set) Proxy.newProxyInstance(s.getClass().getClassLoader(), new Class[]{Set.class}, new LoggingInvocationHandler<>(s));
    }

    public static void main(String[] args) {
        Set s = getSetProxy(new HashSet());
        s.add("three");
        if (!s.contains("four")){
            s.add("four");
        }
        System.out.println(s);
    }



}
