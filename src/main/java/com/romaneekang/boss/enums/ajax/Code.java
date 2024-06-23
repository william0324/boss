package com.romaneekang.boss.enums.ajax;

import lombok.Getter;

@Getter
public enum Code {
    SUCCESS(1000,"业务处理完成"),
    UNKNOWN(1001,"请稍后重试"),
    OPERATOR_PARAM_ERR(1002,"请求数据错误"),
    OPERATOR_LOGIN_ERR(1003,"登录名称或密码错误"),
    OPERATOR_LOGIN_REDIS_ERR(1004,"登录信息存储异常"),
    OPERATOR_LOGIN_JWT_INVALID(1005,"jwt无效，请重新登录"),
    USER_LOGIN_NAME_EXIST(1006,"登录名称已存在"),
    USER_INVALID_NOT_USE(1007,"商户不可用"),
    USER_EDIT_ERR(1008,"编辑商户状态失败"),
    PRODUCT_CODE_EXIST(1009,"支付产品编码已存在，请更换"),
    PRODUCT_NAME_EXIST(1010,"支付产品名称已存在，请更换"),
    PRODUCT_WAY_TYPE_EXIST(1011,"支付产品已经配置了相同的支付方式和类型"),
    PRODUCT_NOT_EXIST(1012,"支付产品不存在")

    ;

    private final int code;
    private final String message;
    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
