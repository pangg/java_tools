package com.xxx.sboot_mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xxx.sboot_mall.model.dao")
public class SbootMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootMallApplication.class, args);
    }

}
