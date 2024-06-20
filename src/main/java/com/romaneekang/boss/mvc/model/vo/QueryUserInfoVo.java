package com.romaneekang.boss.mvc.model.vo;

import lombok.Data;

@Data
public class QueryUserInfoVo {
    private String id;
    private String loginName;
    private String phone;
    private String shopName;
    private String shopScope;
    private String userEmail;
}
