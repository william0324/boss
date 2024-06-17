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
 * 资金账户流水表
 * @TableName user_account_history
 */
@TableName(value ="user_account_history")
@Data
public class UserAccountHistory implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户id
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 账号id
     */
    @TableField(value = "account_no")
    private String accountNo;

    /**
     * 本次操作金额值（-100 ， +100）
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 操作后的账户余额
     */
    @TableField(value = "balance")
    private BigDecimal balance;

    /**
     * 编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 备注（ADD，SUB）
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}