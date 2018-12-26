package com.demo.shrek.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.demo.shrek.upms.dao")
public class ShrekUpmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShrekUpmsApplication.class,args);
    }
}
