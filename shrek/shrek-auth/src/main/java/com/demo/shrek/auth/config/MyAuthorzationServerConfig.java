package com.demo.shrek.auth.config;


import com.demo.shrek.auth.security.CustomRedisTokenStore;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class MyAuthorzationServerConfig extends AuthorizationServerConfigurerAdapter{

    private final DataSource dataSource;

    private final RedisConnectionFactory redisConnectionFactory;

    private final UserDetailsService userDetailsService;

    /**
     * 注入authenticationManager
     * 来支持 password grant type
     */
    private final AuthenticationManager authenticationManager;

    public MyAuthorzationServerConfig(HikariDataSource dataSource, RedisConnectionFactory redisConnectionFactory, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.dataSource = dataSource;
        this.redisConnectionFactory = redisConnectionFactory;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .realm("oauth2-resources") //code授权添加
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //allow check token
                .allowFormAuthenticationForClients();
    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                //允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore(this.redisConnectionFactory))
                .userDetailsService(this.userDetailsService);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.withClientDetails(clientDetailsService());

//
//        String finalSecret=passwordEncoder().encode("demoAppSecret");
//        clients.inMemory()
//                .withClient("demoApp")
//                .secret(finalSecret)
//                .redirectUris("http://baidu.com")//code授权添加
//                .authorizedGrantTypes("authorization_code","client_credentials", "password", "refresh_token")
//                .scopes("all")
//                .resourceIds("oauth2-resource")
//                .accessTokenValiditySeconds(1200)
//                .refreshTokenValiditySeconds(50000);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new CustomRedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

}
