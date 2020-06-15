package com.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 扫描需要暴露的服务
@EnableDubbo(scanBasePackages = "com.example.service")
// 开启 @EnableDiscoveryClient 注解，当前版本默认会开启该注解
//@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}