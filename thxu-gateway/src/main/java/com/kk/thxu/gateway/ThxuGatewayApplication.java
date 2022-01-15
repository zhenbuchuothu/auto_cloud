package com.kk.thxu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ThxuGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThxuGatewayApplication.class, args);
    }

}
