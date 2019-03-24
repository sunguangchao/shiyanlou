package consistent_hashing;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/12 1:07 PM
 * Fowler-Noll-Vo算法，能快速hash大量数据并保持较小的冲突率，
 * 适用于URL,hostname,文件名,text,IP等
 */
public class FnvHashStrategy implements HashStrategy {

    private static final long FNV_32_INIT = 2166136261L;
    private static final int FNV_32_PRIME = 1677619;
    @Override
    public int getHashCode(String origin) {
        final int p = FNV_32_PRIME;
        int hash = (int) FNV_32_INIT;
        for (int i = 0; i < origin.length(); i++){
            hash = (hash ^ origin.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        hash = Math.abs(hash);
        return hash;
    }
}
