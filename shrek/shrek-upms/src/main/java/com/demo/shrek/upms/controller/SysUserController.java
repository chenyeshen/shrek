package com.demo.shrek.upms.controller;

import com.demo.core.util.CommonUtil;
import com.demo.shrek.upms.service.SysUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public JSONObject insert(@RequestBody JSONObject requestJson) {
        return sysUserService.insert(requestJson);
    }

    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return sysUserService.update(requestJson);
    }

    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        return sysUserService.delete(Long.valueOf(request.getParameter("id")));
    }

    @GetMapping
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= CommonUtil.request2Json(request);
        return sysUserService.list(jsonObject);
    }

    @GetMapping("/info/{username}")
    public JSONObject info(@PathVariable String username) {
        System.out.println( SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return sysUserService.info(username);
    }

    @GetMapping("/getInfo")
    public JSONObject getInfo() {
        System.out.println( SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return sysUserService.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}