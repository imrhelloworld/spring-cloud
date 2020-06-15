package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${project.name:}")
    private String projectName;

    @Value("${project.org:}")
    private String projectOrg;

    @GetMapping("/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> configMap = new HashMap();
        configMap.put("projectName", projectName);
        configMap.put("projectOrg", projectOrg);
        return configMap;
    }

    @Value("${common.name:}")
    private String commonName;

    @Value("${global.name:}")
    private String globalName;

    @Value("${refresh.name:}")
    private String refreshName;

    @GetMapping("/extConfig")
    public Map<String, Object> getExtConfig() {
        Map<String, Object> configMap = new HashMap();
        configMap.put("commonName", commonName);
        configMap.put("globalName", globalName);
        configMap.put("refreshName", refreshName);
        return configMap;
    }

}