package com.kk.thxu.gateway.filter;

import com.kk.thxu.common.entity.ThxuResponse;
import com.kk.thxu.common.utils.ThxuUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
//自定义Zuul异常处理
@Slf4j
@Component
public class ThxuGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            ThxuResponse thxuResponse = new ThxuResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            thxuResponse = resolveExceptionMessage(message, serviceId, thxuResponse);

            HttpServletResponse response = ctx.getResponse();
            ThxuUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, thxuResponse
            );
            log.error("Zull sendError：{}", thxuResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private ThxuResponse resolveExceptionMessage(String message, String serviceId, ThxuResponse thuxResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return thuxResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return thuxResponse.message(serviceId + "服务不可用");
        }
        return thuxResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}