package com.kk.thxu.auth.properties;

import lombok.Data;
//Client配置类
@Data
public class ThxuClientsProperties {
    private String client;
    private String secret;
    //当前令牌支持的认证类型
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
