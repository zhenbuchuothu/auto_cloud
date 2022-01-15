package com.kk.thxu.auth;

import com.kk.thxu.common.annotation.EnableThxuAuthExceptionHandler;
import com.kk.thxu.common.annotation.EnableThxuLettuceRedis;
import com.kk.thxu.common.annotation.EnableThxuServerProtect;
import com.kk.thxu.common.annotation.ThxuCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//@ThxuCloudApplication //集成三个注解
@EnableThxuAuthExceptionHandler
@MapperScan("com.kk.thxu.auth.mapper")
@EnableThxuServerProtect //拦截器 检查是否携带 Zuul Token请求头 防越过网关非法访问
@EnableThxuLettuceRedis //基于lettuce的redis配置
public class ThxuAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThxuAuthApplication.class, args);
    }

}
