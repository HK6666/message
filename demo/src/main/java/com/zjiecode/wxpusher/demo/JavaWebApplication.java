package com.zjiecode.wxpusher.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebApplication.class, args);
    }
}
