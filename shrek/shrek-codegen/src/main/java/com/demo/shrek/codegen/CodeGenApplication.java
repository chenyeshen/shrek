package com.demo.shrek.codegen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.shrek.codegen.dao")
public class CodeGenApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(CodeGenApplication.class, args);
    }
}
