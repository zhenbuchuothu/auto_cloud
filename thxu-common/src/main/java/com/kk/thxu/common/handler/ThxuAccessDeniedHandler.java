package com.kk.thxu.common.handler;

import com.kk.thxu.common.entity.ThxuResponse;
import com.kk.thxu.common.utils.ThxuUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//chuli 处理403
public class ThxuAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ThxuResponse thxuResponse = new ThxuResponse();
        ThxuUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, thxuResponse.message("没有权限访问该资源"));
    }
}