package com.kk.thxu.server.system.config;

import com.kk.thxu.common.handler.ThxuAccessDeniedHandler;
import com.kk.thxu.common.handler.ThxuAuthExceptionEntryPoint;
import com.kk.thxu.server.system.properties.ThxuServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置类
 */
@Configuration
@EnableResourceServer
public class ThxuServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private ThxuAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private ThxuAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private ThxuServerSystemProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
/*//        .and()
//                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll();*/
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }


}
