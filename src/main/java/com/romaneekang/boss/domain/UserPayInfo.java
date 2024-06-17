package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 该表用来存放用户开通的第三方支付信息
 * @TableName user_pay_info
 */
@TableName(value ="user_pay_info")
@Data
public class UserPayInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 支付产品编码
     */
    @TableField(value = "pay_product_code")
    private String payProductCode;

    /**
     * 用户
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 商户名称
     */
    @TableField(value = "shop_name")
    private String shopName;

    /**
     * 支付方式编号
     */
    @TableField(value = "pay_way_code")
    private String payWayCode;

    /**
     * 支付方式名称
     */
    @TableField(value = "pay_way_name")
    private String payWayName;

    /**
     * 配置信息，appid， appkey，秘钥文件等，json格式
     */
    @TableField(value = "pay_config")
    private String payConfig;

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