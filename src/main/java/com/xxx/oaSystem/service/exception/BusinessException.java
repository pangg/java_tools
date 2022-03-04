package com.xxx.oaSystem.service.exception;

/**
 * 业务逻辑异常
 */
public class BusinessException extends RuntimeException{
    private String code; // 异常编码code
    private String message;  // 异常的具体信息

    public BusinessException(String code, String msg) {
        super(code + ":" +msg);
        this.code = code;
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
