package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付产品表
 * @TableName pay_product
 */
@TableName(value ="pay_product")
@Data
public class PayProduct implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 支付产品编号
     */
    @TableField(value = "product_code")
    private String productCode;

    /**
     * 支付产品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 产品状态(ENABLE, DISABLE)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 备注，给哪个商户使用的
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}