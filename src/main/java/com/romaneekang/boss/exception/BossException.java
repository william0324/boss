package com.romaneekang.boss.exception;

import com.romaneekang.boss.enums.ajax.Code;
import lombok.Data;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BossException extends RuntimeException {
    private Code code;
    public BossException(String message) {
        super(message);
    }
    public BossException(Code code) {
        super(code.getMessage());
        this.code = code;
    }
    public BossException() {
        super();
    }
}
