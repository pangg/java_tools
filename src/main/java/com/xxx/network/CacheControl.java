package com.xxx.network;

import org.springframework.util.StringUtils;

import java.util.Date;

public class CacheControl {
    private Date maxAge = null;


    public CacheControl(String s) {
        int index = s.indexOf("=") + 1;
        String expires = s.substring(index);
        expires = StringUtils.isEmpty(expires) ? "0" : expires;
        Long expiresNum = 0L;
        try {
            expiresNum = (Long.parseLong(expires)*1000);
        } catch (Exception e) {
            // e.printStackTrace();
            expiresNum = 0L;
        }
        System.out.println(expires);
        Date date = new Date(System.currentTimeMillis() + expiresNum);
        this.maxAge = date;
    }

    public Date getMaxAge() {
        return maxAge;
    }
}
