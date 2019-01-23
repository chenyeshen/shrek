package com.demo.shrek.auth.endpoint;

import com.demo.shrek.auth.security.CustomRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Autowired
    private  RedisConnectionFactory redisConnectionFactory;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public String revokeToken(@RequestHeader(value = "token", required = false) String authHeader) {
        System.out.println("============="+authHeader);
        String tokenValue = authHeader.replace("bearer", "").trim();
        tokenStore(this.redisConnectionFactory).removeAccessToken(tokenStore(this.redisConnectionFactory).readAccessToken(tokenValue));
        System.out.println("注销成功");
        return "注销成功";
    }

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new CustomRedisTokenStore(redisConnectionFactory);
    }
}
