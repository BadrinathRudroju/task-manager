package com.badrinath.task_manager.controller;

import com.badrinath.task_manager.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("Api/v1/info/advanced")
public class infoControllerAdvanced {

    private AppProperties appProperties;

    public infoControllerAdvanced(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping
    public Map<String, Object> getInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("appName", appProperties.getName());
        map.put("appVersion", appProperties.getVersion());
        map.put("maxTasksPerPage", appProperties.getMaxTasksPerPage());
        return map;
    }
}
