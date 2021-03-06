package com.xxx.sboot_mall.exception;

public enum ImoocMallExceptionEnum {
    NEED_USER_NAME(10001, "用户名称不能为空"),
    NEED_PASSWORD(10002, "用户秘密不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能少于8位"),
    NAME_EXISTED(10004, "不允许重名"),
    INSERT_FAILED(10005, "插入失败，请重试"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "用户未登录"),
    UPDATE_FAILED(10008, "更新失败"),
    NEED_ADMIN(10009, "需要管理员权限"),
    PARA_NOT_NULL(10010, "参数不能为空"),
    create_failed(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    DELETE_FAILED(10013, "删除失败"),

    SYSTEM_ERROR(20000, "系统异常");

    /**
     * 异常码
     */
    Integer code;

    /**
     * 异常信息
     */
    String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
