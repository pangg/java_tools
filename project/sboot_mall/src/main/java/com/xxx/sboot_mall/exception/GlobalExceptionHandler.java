package com.xxx.sboot_mall.exception;

import com.xxx.sboot_mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiRestResponse<Object> handleException(Exception e) {
        log.error("Default Exception: ", e);
        return ApiRestResponse.error(ImoocMallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(ImoocMallException.class)
    @ResponseBody
    public ApiRestResponse<Object> handleException(ImoocMallException e) {
        log.error("ImoocMallException: ", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());
    }
}
