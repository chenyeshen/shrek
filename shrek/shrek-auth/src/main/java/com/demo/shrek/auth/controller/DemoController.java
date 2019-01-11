package com.demo.shrek.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.shrek.auth.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private RemoteUserService remoteUserService;

    @RequestMapping("/blog/{id}")
    public String getBlogById(@PathVariable long id) {
        return "this is blog " + id;
    }

    @RequestMapping("/username/{id}")
    public JSONObject getBlogByUsername(@PathVariable String id) {
        System.out.println("11111111111111");
        return remoteUserService.info(id);
    }
}
