package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dic_pay_type
 */
@TableName(value ="dic_pay_type")
@Data
public class DicPayType implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付类型编号
     */
    @TableField(value = "pay_type_code")
    private String payTypeCode;

    /**
     * 支付类型名称
     */
    @TableField(value = "pay_type_name")
    private String payTypeName;

    /**
     * 支付方式编号
     */
    @TableField(value = "pay_way_code")
    private String payWayCode;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}