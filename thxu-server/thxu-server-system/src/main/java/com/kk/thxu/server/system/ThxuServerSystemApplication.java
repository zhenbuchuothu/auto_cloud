package com.kk.thxu.server.system;

import com.kk.thxu.common.annotation.EnableThxuAuthExceptionHandler;
import com.kk.thxu.common.annotation.EnableThxuServerProtect;
import com.kk.thxu.common.annotation.ThxuCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启spring cloud security 权限
//@ThxuCloudApplication //集成三个注解
@EnableThxuAuthExceptionHandler
@EnableThxuServerProtect //拦截器 检查是否携带 Zuul Token请求头 防越过网关非法访问
@EnableFeignClients

@MapperScan("com.kk.thxu.server.system.mapper")
@EnableTransactionManagement
public class ThxuServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThxuServerSystemApplication.class, args);
    }

}
