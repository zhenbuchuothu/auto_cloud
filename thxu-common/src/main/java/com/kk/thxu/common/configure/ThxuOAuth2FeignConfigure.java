package com.kk.thxu.common.configure;

import com.kk.thxu.common.entity.ThxuConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

public class ThxuOAuth2FeignConfigure {
    //fegin拦截器
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {

            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(ThxuConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(ThxuConstant.ZUUL_TOKEN_HEADER, zuulToken);


            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}
