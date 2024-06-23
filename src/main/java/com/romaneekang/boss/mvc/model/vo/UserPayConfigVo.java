package com.romaneekang.boss.mvc.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPayConfigVo {

    private String id;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 支付产品编号
     */
    private String productCode;

    /**
     * 支付产品名称
     */
    private String productName;

    /**
     * 风险预存期
     */
    private Integer riskDay;

    /**
     * 对接入支付系统时需传入的商家唯一标识
     */
    private String appKey;

    /**
     * 商家调用需要签名API的密钥，用作加密
     */
    private String appSecret;

    /**
     * 商户服务器IP
     */
    private String merchantServerIp;

    /**
     * 自动结算
     */
    private String autoSett;

    /**
     * 编辑时间
     */
    private String editTime;
}