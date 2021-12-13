package com.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

//@Configuration
public class WebSecurtiyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/hello/login").permitAll() //登录页面跳转请求
                .loginProcessingUrl("/login").permitAll() //登录发起的post请求方法
                .successForwardUrl("/success").permitAll() //登录成功页面
                .failureForwardUrl("/fail"); //无权限页面

        http.authorizeRequests() // 认证配置
                .antMatchers("/hello/login","/hello/").permitAll() //设置哪些路径可以直接访问，不需要认证
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份验证
                .and().csrf().disable(); //关闭cors
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER");
    }
    
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }
}
