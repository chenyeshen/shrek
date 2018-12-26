package com.demo.shrek.upms.dao;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
/**
 * ${comments}
 * 
 * @author Wushu
 * @email 156810150@qq.com
 * @date 2018-12-26 10:30:06
 */

public interface SysUserDao {
    int update(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    List<JSONObject> listByPage(JSONObject jsonObject);

    int delete(Long id);

    int count(JSONObject jsonObject);

    List<JSONObject> getByUserName(JSONObject jsonObject);
}
