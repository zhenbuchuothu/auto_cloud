package com.kk.thxu.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:thxu-auth.properties"})
@ConfigurationProperties(prefix = "thxu.auth")
public class ThxuAuthProperties {

    private ThxuClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private ThxuValidateCodeProperties code = new ThxuValidateCodeProperties();
}
