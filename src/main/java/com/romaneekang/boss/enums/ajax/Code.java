package com.romaneekang.boss.enums.ajax;

import lombok.Getter;

@Getter
public enum Code {
    SUCCESS(1000,"业务处理完成"),
    UNKNOWN(1001,"请稍后重试"),
    OPERATOR_PARAM_ERR(1002,"请求数据错误"),
    OPERATOR_LOGIN_ERR(1003,"登录名称或密码错误"),
    OPERATOR_LOGIN_REDIS_ERR(1004,"登录信息存储异常"),
    ;

    private final int code;
    private final String message;
    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
