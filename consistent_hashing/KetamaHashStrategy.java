package consistent_hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/12 10:07 PM
 * Ketama算法，有自己一整套流程的一致性哈希算法
 */
public class KetamaHashStrategy implements HashStrategy {
    private static MessageDigest md5Digest;

    static {
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("MD5 not supported", e);
        }
    }

    @Override
    public int getHashCode(String origin) {
        byte[] bKey = computeMd5(origin);
        long rv = ((long) (bKey[3] & 0xFF) << 24)
                |((long) (bKey[2] & 0xFF) << 16)
                |((long) (bKey[1] & 0xFF) << 8)
                |(bKey[0] & 0xFF);
        return (int)(rv & 0xffffffffL);
    }

    /**
     * get the md5 of the given key
     * @param k
     * @return
     */
    public static byte[] computeMd5(String k){
        MessageDigest md5;

        try {
            md5 =  (MessageDigest) md5Digest.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException("clone of MD5 not supported", e);
        }
        md5.update(k.getBytes());
        return md5.digest();
    }
}
