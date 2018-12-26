package com.demo.shrek.upms.service;


import com.alibaba.fastjson.JSONObject;
import java.util.List;

/**
 * ${comments}
 *
 * @author Wushu
 * @email 156810150@qq.com
 * @date 2018-12-26 10:30:06
 */
public interface SysUserService {
    JSONObject update(JSONObject jsonObject);

    JSONObject insert(JSONObject jsonObject);

    JSONObject list(JSONObject jsonObject);

    JSONObject delete(Long id);

    JSONObject info(String name);
}