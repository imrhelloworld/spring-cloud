package com.example.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
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

    @Value("${spring.datasource.driver-class-name:}")
    private String driverClassName;
    @Value("${spring.datasource.url:}")
    private String url;
    @Value("${spring.datasource.username:root}")
    private String username;
    @Value("${spring.datasource.password:root}")
    private String password;

    @GetMapping("/datasourceByAnnotation")
    public Map<Object, Object> getDatasourceByAnnotation() {
        // JDK9中的新特性，快速创建只读集合。
        return Map.of("driverClassName", driverClassName,
                "url", url,
                "username", username,
                "password", password);
    }

    @GetMapping("/datasource")
    public Map<Object, Object> getDatasource() {
        //ConfigService.getAppConfig(); // 读取默认 Namespace
        // 读取指定 Namespace
        Config config = ConfigService.getConfig("application-mysql");
        // 获取配置信息，第一个参数为配置项的 key，第二个参数为默认值（读取不到配置就会使用默认值，建议都加上默认值）
        String driverClassName = config.getProperty("spring.datasource.driver-class-name", null);
        String url = config.getProperty("spring.datasource.url", null);
        String username = config.getProperty("spring.datasource.username", null);
        String password = config.getProperty("spring.datasource.password", null);

        // JDK9中的新特性，快速创建只读集合。
        return Map.of("driverClassName", driverClassName,
                "url", url,
                "username", username,
                "password", password);
    }

    @Value("${commonName:}")
    private String commonName;

    @GetMapping("/commonByAnnotation")
    public Map<Object, Object> getCommonByAnnotation() {
        return Map.of("commonName", commonName);
    }

    @GetMapping("/common")
    public Map<Object, Object> getCommon() {
        //ConfigService.getAppConfig(); // 读取默认 Namespace
        // 读取指定 Namespace
        Config config = ConfigService.getConfig("microservice.application-common");
        // 获取配置信息，第一个参数为配置项的 key，第二个参数为默认值（读取不到配置就会使用默认值，建议都加上默认值）
        String commonName = config.getProperty("commonName", null);
        return Map.of("commonName", commonName);
    }

}