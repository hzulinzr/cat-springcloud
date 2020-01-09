package com.lin.config;

import com.lin.config.security.AuthDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lzr
 * @date 2020-01-09 11:02:19
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthDetailsServiceImpl authDetailsService;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     *  配置用户签名服务
     * @param auth 签名管理器构造器
     */
    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authDetailsService).passwordEncoder(passwordEncoder());

    }

    /**
     *  用来配置拦截保护 请求，比如什么请求放行，什么请求需要验证
     * @param http http 安全请求对象
     * @throws Exception
     */
    @Override
    public void configure (HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .formLogin().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
