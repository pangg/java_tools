package com.xxx.sboot_mall.common;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    public static final String SALT = "abcdefghijklmnopqrstuvwxyz1234567890-=";
    public static final String IMOOC_MALL_USER = "imooc_mall_user";

    public static String FILE_UPLOAD_DIR;

    @Value(value = "${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
