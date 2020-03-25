package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${name}")
    private String name;

    @Value("${password}")
    private String password;

    @GetMapping("/name")
    public String getName() {
        return name;
    }

    @GetMapping("/password")
    public String getPassword() {
        return password;
    }

}