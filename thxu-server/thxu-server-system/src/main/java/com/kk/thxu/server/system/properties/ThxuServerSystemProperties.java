package com.kk.thxu.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:thxu-server-system.properties"})
@ConfigurationProperties(prefix = "thxu.server.system")
public class ThxuServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private ThxuSwaggerProperties swagger = new ThxuSwaggerProperties();
}
