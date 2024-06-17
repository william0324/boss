package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 通知记录日志表 rp_notify_record_log
 * @TableName notify_record_log
 */
@TableName(value ="notify_record_log")
@Data
public class NotifyRecordLog implements Serializable {
    /**
     * 流水主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * 通知id
     */
    @TableField(value = "notify_id")
    private String notifyId;

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
     * 发送的数据
     */
    @TableField(value = "request")
    private String request;

    /**
     * 通知结果
     */
    @TableField(value = "response")
    private String response;

    /**
     * 最后修改时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * http状态码
     */
    @TableField(value = "http_code")
    private String httpCode;

    /**
     * 
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}