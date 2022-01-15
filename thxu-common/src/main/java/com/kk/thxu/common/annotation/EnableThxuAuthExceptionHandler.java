package com.kk.thxu.common.annotation;

import com.kk.thxu.common.configure.ThxuAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
//自定义注解  可以向其他模块的ioc容器注入
@Target({ElementType.TYPE})//注解的适用范围 ， ElementType.TYPE：接口、类、枚举、注解
@Retention(RetentionPolicy.RUNTIME)//注解的保留策略 ，RetentionPolicy.RUNTIME：字节码中存在，运行的时候可以用 反射 得到
@Documented//使用了该注解，就会包含在 Javadoc 中
@Import(ThxuAuthExceptionConfigure.class)
public @interface EnableThxuAuthExceptionHandler {

}
