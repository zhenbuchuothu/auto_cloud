package com.kk.thxu.register.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//安全配置
@EnableWebSecurity
public class ThxuRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {
    /*
        开启Eureka服务端端点保护。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**")
       /**
       * Spring Boot Admin是通过spring-boot-starter-actuator提供的/actuator/**监控接口来实现的，
       * 而我们的微服务都是受Spring Cloud Security保护的，所以我们需要将/actuator/**资源替换为免认证路径中。
       */
                   .and()
                   .authorizeRequests().antMatchers("/actuator/**").permitAll();
        super.configure(http);
    }
}
