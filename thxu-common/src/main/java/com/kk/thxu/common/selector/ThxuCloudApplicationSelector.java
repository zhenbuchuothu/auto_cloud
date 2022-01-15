package com.kk.thxu.common.selector;

import com.kk.thxu.common.configure.ThxuAuthExceptionConfigure;
import com.kk.thxu.common.configure.ThxuOAuth2FeignConfigure;
import com.kk.thxu.common.configure.ThxuServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ThxuCloudApplicationSelector implements ImportSelector {
    /**
     * 通过selectImports方法，我们一次性导入了FebsAuthExceptionConfigure、
     * FebsOAuth2FeignConfigure和FebsServerProtectConfigure这三个配置类。
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                ThxuAuthExceptionConfigure.class.getName(),
                ThxuOAuth2FeignConfigure.class.getName(),
                ThxuServerProtectConfigure.class.getName()
        };
    }
}
