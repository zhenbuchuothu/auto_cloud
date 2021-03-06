package com.kk.thxu.monitor.admin.configure;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 配置了免认证路径，比如/assets/**静态资源和/login登录页面；配置了登录页面为/login，登出页面为/logout。
 *
 * 配置好后，启动febs-monitor-admin模块，使用浏览器访问 http://localhost:8401：
 */
@EnableWebSecurity
public class ThxuSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public ThxuSecurityConfigure(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf().disable();


//        http.authorizeRequests().antMatchers("/actuator/**").permitAll();

    }


}
