package com.kk.thxu.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.kk.thxu.common.entity.ThxuResponse;
import com.kk.thxu.common.utils.ThxuUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//处理资源服务器异常
public class ThxuAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ThxuResponse thxuResponse = new ThxuResponse();
        ThxuUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, thxuResponse.message("token无效")
        );
    }
}