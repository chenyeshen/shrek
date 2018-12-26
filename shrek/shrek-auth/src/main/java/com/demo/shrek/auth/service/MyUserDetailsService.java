package com.demo.shrek.auth.service;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.demo.shrek.auth.feign.RemoteUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) {

        return  getUserDetails(remoteUserService.info(userName));

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        String finalPassword = bCryptPasswordEncoder.encode("123456");
//
//        return User.withUsername("demoUser1").password(finalPassword).authorities("USER").build();
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(JSONObject result) {
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return User.withUsername(result.getString("username")).password(result.getString("password")).authorities("USER").build();
    }
}
