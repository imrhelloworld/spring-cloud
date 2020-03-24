package com.example.config;

import com.example.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

/**
 * 网关路由配置类
 */
// @Configuration
public class GatewayRoutesConfiguration {

    // @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(r -> r.path("/product/**")
                // 目标 URI，路由到微服务的地址
                .uri("lb://product-service")
                // 注册自定义网关过滤器
                .filters(new CustomGatewayFilter())
                // 路由 ID，唯一
                .id("product-service"))
                .build();
    }

}
