package com.demo.shrek.auth.feign;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shrek-upms")
public interface RemoteUserService {
    @RequestMapping(value = "/sysUser/info/{username}",method = RequestMethod.GET)
    JSONObject info(@RequestParam(value = "username") String name);
}
