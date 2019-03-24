package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/20 5:19 PM
 */
public class LoggingInvocationHandler<T> implements InvocationHandler {
    final T underlying;

    public LoggingInvocationHandler(T underlying){
        this.underlying = underlying;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName()).append("(");
        for (int i=0; args != null && i < args.length; i++){
            if (i != 0){
                sb.append(",");
            }
            sb.append(args[i]);
        }
        sb.append(")");
        Object ret = method.invoke(underlying, args);
        if (ret != null){
            sb.append("->");
            sb.append(ret);
        }
        System.out.println(sb);
        return ret;
    }
}
