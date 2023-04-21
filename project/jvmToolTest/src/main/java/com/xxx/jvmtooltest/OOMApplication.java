package com.xxx.jvmtooltest;

import com.xxx.jvmtooltest.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OOMApplication implements CommandLineRunner {

    @Autowired
    FooService fooService;

    public static void main(String[] args) {

        SpringApplication.run(OOMApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        //程序启动后，不断调用Fooservice.oom()方法

        while (true) {

            fooService.oom();

        }

    }
}
