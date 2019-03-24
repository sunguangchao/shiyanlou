package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/20 8:26 PM
 */
public class JavaBeanProxyFactory {

    private static class JavaBeanProxy implements InvocationHandler{
        Map<String, Object> properties = new HashMap<>();

        public JavaBeanProxy(Map<String, Object> properties){
            this.properties = properties;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String meth = method.getName();
            if (meth.startsWith("get")){
                String prop = meth.substring(3);
                Object o = properties.get(prop);
                if (o != null && !method.getReturnType().isInstance(o)){
                    throw new ClassCastException(o.getClass().getName() + "is not a" + method.getReturnType().getName());
                }
                return o;
            }else if (meth.startsWith("set")){

            }else if (meth.startsWith("is")){

            }
            return null;
        }
    }

    public static <T> T getProxy(Class<T> intf, Map<String, Object> values){
        return (T)Proxy.newProxyInstance(JavaBeanProxyFactory.class.getClassLoader(), new Class[]{intf}, new JavaBeanProxy(values));
    }
}
