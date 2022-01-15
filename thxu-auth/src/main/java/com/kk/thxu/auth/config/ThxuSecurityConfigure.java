package com.kk.thxu.auth.config;

import com.kk.thxu.auth.filter.ValidateCodeFilter;
import com.kk.thxu.auth.service.ThxuUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ThxuSecurityConfigure用于处理/oauth开头的请求，Spring Cloud OAuth内部定义的获取令牌，
 * 刷新令牌的请求地址都是以/oauth/开头的，也就是说ThxuSecurityConfigure用于处理和令牌相关的请求；
 */

@Order(2)  //因为ThxuResourceServerConfigure的config的order是3 并且是对所有请求都生效。所以我们要提高用户访问/oauth/的order优先级
@EnableWebSecurity//开启和web相关的安全配置
public class ThxuSecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private ThxuUserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

//    /**
//     * BCryptPasswordEncoder可以对于一个相同的密码，每次加密出来的加密串都不同
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     *  对/oauth/开头的请求有效
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //将validateCodeFilter验证码过滤器添加到UsernamePasswordAuthenticationFilter校验用户名密码过滤器前面
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();

//        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        super.configure(http);



    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
