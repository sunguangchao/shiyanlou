package consistent_hashing;

import java.util.zip.CRC32;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/12 10:04 PM
 */
public class CRCHashStrategy implements HashStrategy{
    @Override
    public int getHashCode(String origin) {
        CRC32 crc32 = new CRC32();
        crc32.update(origin.getBytes());
        return (int)((crc32.getValue() >> 16) & 0x7fff & 0xffffffffL);
    }
}
