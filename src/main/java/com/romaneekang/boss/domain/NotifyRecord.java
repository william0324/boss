package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 通知记录表 rp_notify_record
 * @TableName notify_record
 */
@TableName(value ="notify_record")
@Data
public class NotifyRecord implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 商户号
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 商户订单号
     */
    @TableField(value = "user_order_no")
    private String userOrderNo;

    /**
     * 通知地址
     */
    @TableField(value = "notify_url")
    private String notifyUrl;

    /**
     * 通知次数
     */
    @TableField(value = "notify_times")
    private Integer notifyTimes;

    /**
     * 最大通知次数
     */
    @TableField(value = "max_notify_times")
    private Integer maxNotifyTimes;

    /**
     * 状态(成功，失败，异常)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 最后修改时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}