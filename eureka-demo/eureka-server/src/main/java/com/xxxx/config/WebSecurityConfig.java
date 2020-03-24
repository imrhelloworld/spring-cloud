package com.xxxx.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全认证配置类
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http); // 加这句是为了访问 eureka 控制台和 /actuator 时能做安全控制
        http.csrf().ignoringAntMatchers("/eureka/**"); // 忽略 /eureka/** 的所有请求
    }

}