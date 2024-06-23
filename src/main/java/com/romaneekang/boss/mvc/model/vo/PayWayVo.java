package com.romaneekang.boss.mvc.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayWayVo {
    private String id;

    /**
     * 支付方式编号
     */
    private String wayCode;

    /**
     * 支付方式名称
     */
    private String wayName;

    /**
     * 支付类型编号
     */
    private String typeCode;

    /**
     * 支付类型名称
     */
    private String typeName;

    /**
     * 支付产品编号
     */
    private String productCode;

    /**
     * 商户支付费率
     */
    private String payRate;

    /**
     * 排序
     */
    private String sorts;

    /**
     * 状态 ENABLE, DISABLE
     */
    private String status;

    /**
     * 修改时间
     */
    private String editTime;
}
