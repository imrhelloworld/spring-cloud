package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 开启 EurekaClient 注解，当前版本如果配置了 Eureka 注册中心，默认会开启该注解
//@EnableEurekaClient
@SpringBootApplication
public class OrderService02Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderService02Application.class, args);
    }

}