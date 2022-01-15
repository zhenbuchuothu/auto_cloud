package com.kk.thxu.common.annotation;

import com.kk.thxu.common.selector.ThxuCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ThxuCloudApplicationSelector.class)
public @interface ThxuCloudApplication {
}
