package com.iviui.platform.framework.utils;

public enum ResultCode {
    SUCCESS(200,"成功"),
    FAILED(601,"操作失败"),
    AUTH_ERROR(401,"认证失败"),
    SYS_ERROR(500,"系统错误"),
    PARAM_ERROR(400,"参数错误"),
    UNKNOWN_ERROR(499,"未知错误");

    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
