package com.demo.shrek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册类
 */

@SpringBootApplication
@EnableEurekaServer
public class ShrekServiceApplicatioin {
    public static void main(String[] args) {
        SpringApplication.run( ShrekServiceApplicatioin.class, args );
    }
}
