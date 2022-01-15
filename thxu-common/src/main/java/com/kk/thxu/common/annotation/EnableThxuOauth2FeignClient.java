package com.kk.thxu.common.annotation;

import com.kk.thxu.common.configure.ThxuOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ThxuOAuth2FeignConfigure.class)
public @interface EnableThxuOauth2FeignClient {
}
