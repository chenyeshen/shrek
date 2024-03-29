package com.demo.shrek.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@MapperScan("com.demo.shrek.upms.dao")
@EnableEurekaClient
@EnableResourceServer
@EnableDiscoveryClient
public class ShrekUpmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShrekUpmsApplication.class,args);
    }
}
