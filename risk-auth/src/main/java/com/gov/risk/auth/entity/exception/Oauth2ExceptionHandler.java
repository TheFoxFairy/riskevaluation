package com.gov.risk.auth.entity.exception;

import com.gov.risk.auth.api.CommonResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局处理Oauth2抛出的异常
 *
 */
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult<String> handleOauth2(OAuth2Exception e) {
        return CommonResult.failed(e.getMessage());
    }
}
