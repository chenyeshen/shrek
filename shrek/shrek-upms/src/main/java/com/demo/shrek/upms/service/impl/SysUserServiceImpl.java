package com.demo.shrek.upms.service.impl;


import com.demo.shrek.upms.dao.SysUserDao;
import com.alibaba.fastjson.JSONObject;

import com.demo.shrek.upms.service.SysUserService;
import java.util.List;

import com.demo.shrek.upms.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${comments}
 *
 * @author Wushu
 * @email 156810150@qq.com
 * @date 2018-12-26 10:30:06
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public JSONObject update(JSONObject jsonObject){
        sysUserDao.update(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject insert(JSONObject jsonObject){
        sysUserDao.insert(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = sysUserDao.count(jsonObject);
        List<JSONObject> list = sysUserDao.listByPage(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject delete (Long id){
        sysUserDao.delete(id);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject info(String userName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",userName);
        List<JSONObject> list = sysUserDao.getByUserName(jsonObject);

        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }

    }
}