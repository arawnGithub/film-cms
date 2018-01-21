package com.arawn.film.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security 配置
 * Created by ArawN on 2017/12/28.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123456")
                .roles("ADMIN");
    }

    /**
     * 请求授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().headers().disable()
            .authorizeRequests()
            .antMatchers("/admin/**").authenticated() // 配置需要身份认证的请求地址
            .anyRequest().permitAll() // 其余请求不需要身份认证
            .and()
            .formLogin()
            .loginPage("/login") // 指定登录请求地址
            .defaultSuccessUrl("/admin") // 登录成功后的跳转URL
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login") // 注销成功后的跳转URL
            .permitAll();
    }
}
