package bloom_filter;

import java.util.zip.CRC32;

/**
 * 布隆过滤器实现
 * @Author: sunguangchao
 * @Date: 2019/3/12 9:43 PM
 * 前提：如何判断一个元素在亿级数据中是否存在？
 * 对写入的数据做H次hash运算定位到数组中的位置，同时将数据改为1.
 * 当有数据查询的时候也是同样的方式定位到数组中
 * 一旦其中的有一位为0则认为数据肯定不存在于集合，
 * 否则数据可能存在于集合中
 *
 */
public class BloomFilter {
    private static final long FNV_32_INIT = 2166136261L;
    private static final int FNV_32_PRIME = 1677619;

    private int arraySize;

    private int[] array;

    public BloomFilter(int arraySize) {
        this.arraySize = arraySize;
        array = new int[arraySize];
    }

    private int jdkHash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    private int fnvHash(String origin) {
        final int p = FNV_32_PRIME;
        int hash = (int) FNV_32_INIT;
        for (int i = 0; i < origin.length(); i++) {
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

    private int crcHash(String origin) {
        CRC32 crc32 = new CRC32();
        crc32.update(origin.getBytes());
        return (int) ((crc32.getValue() >> 16) & 0x7fff & 0xffffffffL);
    }


    public void add(String key){
        /**
         * 这里用了三种不同的hash算法
         */
        int first = jdkHash(key);
        int second = fnvHash(key);
        int third = crcHash(key);

        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;
    }

    public boolean check(String key){
        int first = jdkHash(key);
        int second = fnvHash(key);
        int third = crcHash(key);
        if (array[first % arraySize] == 0){
            return false;
        }
        if (array[second % arraySize] == 0){
            return false;
        }
        if (array[third% arraySize] == 0){
            return false;
        }
        return true;
    }
}
