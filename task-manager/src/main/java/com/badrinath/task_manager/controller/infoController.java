package com.badrinath.task_manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("Api/v1/info")
public class infoController {

    @Value("${app.name}")
    private String AppName;

    @Value("${app.version}")
    private String AppVersion;

    @Value("${app-max-tasks-per-page:20}")
    private Long maxTasksPerPage;

    public Map<String, Object> getAppInfo(){
        Map<String, Object> map = new HashMap<>();

        map.put("AppName", AppName);
        map.put("AppVersion", AppVersion);
        map.put("maxTasksPerPage", maxTasksPerPage);
        return map;
    }
}
