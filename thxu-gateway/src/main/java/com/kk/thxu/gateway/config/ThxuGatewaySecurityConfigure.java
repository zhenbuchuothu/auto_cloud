package com.kk.thxu.gateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class ThxuGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();


//        http.csrf().ignoringAntMatchers("/eureka/**")
//                /**
//                 * Spring Boot Admin是通过spring-boot-starter-actuator提供的/actuator/**监控接口来实现的，
//                 * 而我们的微服务都是受Spring Cloud Security保护的，所以我们需要将/actuator/**资源替换为免认证路径中。
//                 */
//                .and()
//                .authorizeRequests().antMatchers("/actuator/**").permitAll();
//        super.configure(http);
    }
}
