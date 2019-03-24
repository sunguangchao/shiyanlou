package consistent_hashing;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 12:57 PM
 */
public interface HashStrategy {
    int getHashCode(String origin);
}
