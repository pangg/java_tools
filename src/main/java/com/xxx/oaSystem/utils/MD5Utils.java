package com.xxx.oaSystem.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    /**
     * MD5
     * @param source 源数据
     * @return MD5摘要
     */
    public static String md5Digest(String source) {
        return DigestUtils.md5Hex(source);
    }

    /**
     * 对源数据加盐混淆后生成MD5摘要
     * @param source 源数据
     * @param salt 盐值
     * @return MD5摘要
     */
    public static String md5Digest(String source, Integer salt) {
         char[] ca = source.toCharArray();
         for (int i = 0; i < ca.length; i++) {
             ca[i] = (char) (ca[i] + salt);
         }
         String target = new String(ca);
         return DigestUtils.md5Hex(target);
    }
}
