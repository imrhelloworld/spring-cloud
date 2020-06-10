package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 开启 FeignClients 注解
@EnableFeignClients
// 开启 @EnableDiscoveryClient 注解，当前版本默认会开启该注解
//@EnableDiscoveryClient
@SpringBootApplication
public class OrderServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceFeignApplication.class, args);
    }

}
