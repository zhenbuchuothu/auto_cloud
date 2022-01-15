package com.kk.thxu.server.system.handler;

import com.kk.thxu.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class SysExceptionHandler extends BaseExceptionHandler {

}
