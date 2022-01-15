package com.kk.thxu.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ThxuRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThxuRegisterApplication.class, args);
    }

}
