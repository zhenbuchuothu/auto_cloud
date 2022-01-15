package com.kk.thxu.server.test;

import com.kk.thxu.common.annotation.EnableThxuAuthExceptionHandler;
import com.kk.thxu.common.annotation.EnableThxuOauth2FeignClient;
import com.kk.thxu.common.annotation.EnableThxuServerProtect;
import com.kk.thxu.common.annotation.ThxuCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ThxuCloudApplication //集成下面三个
//@EnableThxuAuthExceptionHandler//认证类型异常翻译。
//@EnableThxuOauth2FeignClient//开启带令牌的Feign请求，避免微服务内部调用出现401异常；
//@EnableThxuServerProtect //拦截器 检查是否携带 Zuul Token请求头 防越过网关非法访问 开启微服务防护，避免客户端绕过网关直接请求微服务；
public class ThxuServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThxuServerTestApplication.class, args);
    }

}
