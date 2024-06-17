package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限_操作员操作日志表
 * @TableName pms_operator_log
 */
@TableName(value ="pms_operator_log")
@Data
public class PmsOperatorLog implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 操作者id
     */
    @TableField(value = "operator_id")
    private Long operatorId;

    /**
     * 操作者姓名
     */
    @TableField(value = "operator_name")
    private String operatorName;

    /**
     * 操作类型（1:增加，2:修改，3:删除，4:查询）
     */
    @TableField(value = "operate_type")
    private String operateType;

    /**
     * 操作登录ip
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 操作内容描述
     */
    @TableField(value = "content")
    private String content;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 状态（SUCC：操作成功，FAIL：失败）
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}