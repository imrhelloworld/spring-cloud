package com.example;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class OrderService02Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderService02Application.class, args);
    }

}