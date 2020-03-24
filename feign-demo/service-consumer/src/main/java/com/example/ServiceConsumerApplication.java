package com.example;

import com.netflix.loadbalancer.RandomRule;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// 开启 EurekaClient 注解，目前版本如果配置了 Eureka 注册中心，默认会开启该注解
//@EnableEurekaClient
// 开启 FeignClients 注解
@EnableFeignClients
public class ServiceConsumerApplication {

    /*
        NONE：不记录任何信息，默认值
        BASIC：记录请求方法、请求 URL、状态码和用时
        HEADERS：在 BASIC 基础上再记录一些常用信息
        FULL：记录请求和相应的所有信息
     */
    @Bean
    public Logger.Level getLog() {
        return Logger.Level.FULL;
    }

    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

}