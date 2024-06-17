package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 支付方式
 * @TableName pay_way
 */
@TableName(value ="pay_way")
@Data
public class PayWay implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 支付方式编号
     */
    @TableField(value = "way_code")
    private String wayCode;

    /**
     * 支付方式名称
     */
    @TableField(value = "way_name")
    private String wayName;

    /**
     * 支付类型编号
     */
    @TableField(value = "type_code")
    private String typeCode;

    /**
     * 支付类型名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 支付产品编号
     */
    @TableField(value = "pay_product_code")
    private String payProductCode;

    /**
     * 商户支付费率
     */
    @TableField(value = "pay_rate")
    private BigDecimal payRate;

    /**
     * 排序
     */
    @TableField(value = "sorts")
    private Integer sorts;

    /**
     * 状态 ENABLE, DISABLE
     */
    @TableField(value = "status")
    private String status;

    /**
     * 修改时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}