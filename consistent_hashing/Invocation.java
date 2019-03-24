package consistent_hashing;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 9:29 AM
 * 对请求的抽象
 */
public class Invocation {
    private String hashKey;

    public Invocation(String hashKey){
        this.hashKey = hashKey;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
