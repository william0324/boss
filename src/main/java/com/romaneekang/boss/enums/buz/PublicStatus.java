package com.romaneekang.boss.enums.buz;

import lombok.Getter;

/**
 * 公共状态
 */
@Getter
public enum PublicStatus {
    ENABLE("启用"),
    DISABLE("禁用");

    private final String msg;

    PublicStatus(String msg) {
        this.msg = msg;
    }
}
