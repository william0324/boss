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
 * 资金账户表
 * @TableName user_account
 */
@TableName(value ="user_account")
@Data
public class UserAccount implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户编号
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 账号
     */
    @TableField(value = "account_no")
    private String accountNo;

    /**
     * 余额
     */
    @TableField(value = "balance")
    private BigDecimal balance;

    /**
     * 总收入
     */
    @TableField(value = "total_income")
    private BigDecimal totalIncome;

    /**
     * 总支出（提现）
     */
    @TableField(value = "total_expend")
    private BigDecimal totalExpend;

    /**
     * 状态（ENABLE, DISABLE）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}