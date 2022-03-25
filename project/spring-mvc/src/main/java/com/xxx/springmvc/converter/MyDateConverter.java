package com.xxx.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        System.out.println("自定义转换器～");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date d = sdf.parse(s);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }
}
