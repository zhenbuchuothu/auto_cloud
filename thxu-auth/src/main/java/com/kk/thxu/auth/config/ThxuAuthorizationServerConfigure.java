package com.kk.thxu.auth.config;

import com.kk.thxu.auth.properties.ThxuAuthProperties;
import com.kk.thxu.auth.properties.ThxuClientsProperties;
import com.kk.thxu.auth.service.ThxuUserDetailService;
import com.kk.thxu.auth.translator.ThxuWebResponseExceptionTranslator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 在ThxuAuthorizationServerConfigure中，tokenStore使用的是RedisTokenStore，
 * 认证服务器生成的令牌将被存储到Redis中。
 */
@Configuration
@EnableAuthorizationServer//开启认证服务器
public class ThxuAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {
    //ThxuSecurityConfigure中的
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private ThxuUserDetailService userDetailService;
    //ThxuSecurityConfigure中的
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ThxuAuthProperties authProperties;
    //导入异常翻译器类
    private ThxuWebResponseExceptionTranslator exceptionTranslator;

    /**
     * 客户端从认证服务器获取令牌的时候，必须使用client_id为thxu，client_secret为123456的标识来获取；
     * 该client_id支持password模式获取令牌，并且可以通过refresh_token来获取新的令牌；
     * 在获取client_id为thxu的令牌的时候，scope只能指定为all，否则将获取失败；
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ThxuClientsProperties[] clientsArray = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        if (ArrayUtils.isNotEmpty(clientsArray)) {
            for (ThxuClientsProperties client : clientsArray) {
                if (StringUtils.isBlank(client.getClient())) {
                    throw new Exception("client不能为空");
                }
                if (StringUtils.isBlank(client.getSecret())) {
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }

    }

    @Override
    @SuppressWarnings("all")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService )
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(exceptionTranslator);//指定异常翻译器
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);//开启刷新令牌的直吹
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());//令牌的有效时间
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());//刷新令牌的有效时间
        return tokenServices;
    }
}
