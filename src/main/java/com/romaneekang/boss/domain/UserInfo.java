package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 该表用来存放用户的基本信息
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private String id;

    /**
     * 账号
     */
    @TableField(value = "user_no")
    private String userNo;

    /**
     * 商户登录名称
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 资金账号
     */
    @TableField(value = "account_no")
    private String accountNo;

    /**
     * 联系手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 提现密码
     */
    @TableField(value = "pay_pwd")
    private String payPwd;

    /**
     * 商户平台简称
     */
    @TableField(value = "shop_name")
    private String shopName;

    /**
     * 经营范围
     */
    @TableField(value = "shop_scope")
    private String shopScope;

    /**
     * 邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 状态(ENABLE, DISABLE)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}