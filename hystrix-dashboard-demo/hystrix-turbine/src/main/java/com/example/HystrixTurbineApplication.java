package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

// 开启熔断器注解 2 选 1，@EnableHystrix 封装了 @EnableCircuitBreaker
// @EnableHystrix
@EnableCircuitBreaker
// 开启数据监控注解
@EnableHystrixDashboard
// 开启聚合监控注解
@EnableTurbine
@SpringBootApplication
public class HystrixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication.class, args);
    }

}