package consistent_hashing;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 12:59 PM
 */
public class JdkHashCodeStrategy implements HashStrategy {
    @Override
    public int getHashCode(String origin) {
        return origin.hashCode();
    }
}
