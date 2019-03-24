package consistent_hashing;

import java.util.List;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 12:56 PM
 */
public interface LoadBalancer {
    Server select(List<Server> servers, Invocation invocation);
}
