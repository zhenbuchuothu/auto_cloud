package com.kk.thxu.auth.controller;

import com.kk.thxu.auth.service.ValidateCodeService;
import com.kk.thxu.common.entity.ThxuAuthException;
import com.kk.thxu.common.entity.ThxuResponse;
import com.kk.thxu.common.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class SecurityController {
    //获取当前用户
    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    //验证码业务类
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
    //注销
    @DeleteMapping("signout")
    public ThxuResponse signout(HttpServletRequest request) throws ThxuAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        ThxuResponse thxuResponse = new ThxuResponse();
        //注销token
        if (!consumerTokenServices.revokeToken(token)) {
            throw new ThxuAuthException("退出登录失败");
        }
        return thxuResponse.message("退出登录成功");
    }

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }
}
