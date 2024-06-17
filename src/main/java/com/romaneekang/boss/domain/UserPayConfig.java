package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付设置表
 * @TableName user_pay_config
 */
@TableName(value ="user_pay_config")
@Data
public class UserPayConfig implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户编号
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 商户名称
     */
    @TableField(value = "shop_name")
    private String shopName;

    /**
     * 支付产品编号
     */
    @TableField(value = "pay_product_code")
    private String payProductCode;

    /**
     * 支付产品名称
     */
    @TableField(value = "pay_product_name")
    private String payProductName;

    /**
     * 风险预存期
     */
    @TableField(value = "risk_day")
    private Integer riskDay;

    /**
     * 对接入支付系统时需传入的商家唯一标识
     */
    @TableField(value = "app_key")
    private String appKey;

    /**
     * 商家调用需要签名API的密钥，用作加密
     */
    @TableField(value = "app_secret")
    private String appSecret;

    /**
     * 商户服务器IP
     */
    @TableField(value = "merchant_server_ip")
    private String merchantServerIp;

    /**
     * 自动结算
     */
    @TableField(value = "auto_sett")
    private String autoSett;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 状态(ENABLE,DISABLE)
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}