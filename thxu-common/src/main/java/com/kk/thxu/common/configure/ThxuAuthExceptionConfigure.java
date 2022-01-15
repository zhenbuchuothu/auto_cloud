package com.kk.thxu.common.configure;

import com.kk.thxu.common.handler.ThxuAccessDeniedHandler;
import com.kk.thxu.common.handler.ThxuAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class ThxuAuthExceptionConfigure {

    @Bean
    //当IOC容器中没有指定名称或类型的Bean的时候，就注册它。
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public ThxuAccessDeniedHandler accessDeniedHandler(){
        return new ThxuAccessDeniedHandler();
    }


    @Bean
    @ConditionalOnMissingBean(name = "authExceptionEntryPoint")
    public ThxuAuthExceptionEntryPoint authExceptionEntryPoint(){
        return new ThxuAuthExceptionEntryPoint();
    }
}
