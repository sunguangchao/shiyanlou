package consistent_hashing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/13 9:55 AM
 * MurmurHash算法：高运算性，低碰撞率
 * 先已应用到Hadoop、libstdc++、nginx
 *
 */
public class MurmurHashStrategy implements HashStrategy {
    @Override
    public int getHashCode(String origin) {
        ByteBuffer buffer = ByteBuffer.wrap(origin.getBytes());
        int seed = 0x1234ABCD;
        ByteOrder byteOrder = buffer.order();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buffer.remaining() * m);
        long k;

        while (buffer.remaining() > 0){
            k = buffer.getLong();
            k *= m;
            k ^= k >>> r;
            k *= m;
            h ^= k;
            h *= m;
        }

        if (buffer.remaining() > 0){
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            finish.put(buffer).rewind();
            h ^= finish.getLong();
            h *= m;
        }
        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;
        buffer.order(byteOrder);
        return (int)(h & 0xffffffffL);
    }
}
