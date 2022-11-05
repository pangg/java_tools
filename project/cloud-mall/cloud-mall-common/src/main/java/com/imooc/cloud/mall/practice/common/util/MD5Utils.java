package com.imooc.cloud.mall.practice.common.util;

import com.imooc.cloud.mall.practice.common.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具  先用MD5生成某个值，在把这个值用base64加密。
 * MD5生成的时具有不可解密性，所以他不是加密工具。base64才是加密工具
 */
public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes(StandardCharsets.UTF_8)));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5 = MD5Utils.getMD5Str("123456");
        System.out.println(md5);
    }
}
