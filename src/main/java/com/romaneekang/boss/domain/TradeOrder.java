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
 * roncoo pay 龙果支付 支付订单表
 * @TableName trade_order
 */
@TableName(value ="trade_order")
@Data
public class TradeOrder implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 商户编号
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 商家名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 商户订单号
     */
    @TableField(value = "user_order_no")
    private String userOrderNo;

    /**
     * 支付流水号，网关流水号
     */
    @TableField(value = "tx_no")
    private String txNo;

    /**
     * 订单金额(元)
     */
    @TableField(value = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 下单时间
     */
    @TableField(value = "order_time")
    private Date orderTime;

    /**
     * 支付产品编号
     */
    @TableField(value = "pay_product_code")
    private String payProductCode;

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
     * 商家页面地址
     */
    @TableField(value = "pageUrl")
    private String pageurl;

    /**
     * 商家服务器通知地址
     */
    @TableField(value = "notifyUrl")
    private String notifyurl;

    /**
     * 支付备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 最后修改时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 状态（支付成功，失败）
SUCCESS:成功；FAILURE:失败，CREATE:创建；WAIT-PAY:等待支付
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}