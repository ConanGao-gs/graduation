package com.example.demo.config;

import com.example.demo.filter.JwtLoginFilter;
import com.example.demo.user.service.SysUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity      //这个注解的意思是这个类是Spring Security的配置类
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SysUserService userService;

    private final RsaKeyProperties rsaKeyProperties;

    public WebSecurityConfig(SysUserService userService, RsaKeyProperties rsaKeyProperties) throws Exception {
        this.userService = userService;
        this.rsaKeyProperties = rsaKeyProperties;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证用户的来源
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据库中
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置SpringSecurity相关信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //关闭csrf
                .addFilter(new JwtLoginFilter(super.authenticationManager(),rsaKeyProperties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    //禁用session
    }

}
