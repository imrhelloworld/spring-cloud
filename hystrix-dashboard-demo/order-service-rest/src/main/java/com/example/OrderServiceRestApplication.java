package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 开启缓存注解
// @EnableCaching
// 开启熔断器注解 2 选 1，@EnableHystrix 封装了 @EnableCircuitBreaker
// @EnableHystrix
@EnableCircuitBreaker
// 开启数据监控注解
@EnableHystrixDashboard
@SpringBootApplication
public class OrderServiceRestApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceRestApplication.class, args);
    }

}
