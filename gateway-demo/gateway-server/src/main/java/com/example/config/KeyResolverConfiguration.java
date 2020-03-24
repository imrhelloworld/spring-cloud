package com.example.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流规则配置类
 */
@Configuration
public class KeyResolverConfiguration {

    /**
     * 根据 URI 限流
     *
     * @return
     */
    // @Bean
    public KeyResolver pathKeyResolver() {
        /*
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getPath().toString());
            }
        };
        */
        // JDK 1.8
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }

    /**
     * 根据参数限流
     *
     * @return
     */
    // @Bean
    public KeyResolver parameterKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
     * 根据 IP 限流
     *
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}
