package com.example.controller;

import com.example.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConfigController {

    @Autowired
    private ConfigProperties configProperties;

    @Value("${name}")
    private String name;

    @GetMapping("/name")
    public String getName() {
        return configProperties.getName();
    }

    @GetMapping("/mysql")
    public Map<Object, Object> getMySQLProperties() {
        // JDK9中的新特性，快速创建只读集合。
        return Map.of("host", configProperties.getMysqlHost(),
                "port", configProperties.getMysqlPort(),
                "username", configProperties.getMysqlUsername(),
                "password", configProperties.getMysqlPassword());
    }

}