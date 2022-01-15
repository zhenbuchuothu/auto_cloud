package com.kk.thxu.common.annotation;

import com.kk.thxu.common.configure.ThxuServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ThxuServerProtectConfigure.class)
public @interface EnableThxuServerProtect {

}
