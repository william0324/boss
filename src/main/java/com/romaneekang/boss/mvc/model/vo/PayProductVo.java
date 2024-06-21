package com.romaneekang.boss.mvc.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class PayProductVo {
    private String id;
    /**
     * 支付产品编号
     */
    private String productCode;

    /**
     * 支付产品名称
     */
    private String productName;

    /**
     * 产品状态(ENABLE, DISABLE)
     */
    private String status;

    /**
     * 编辑时间
     */
    private String editTime;
}
