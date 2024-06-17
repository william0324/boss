package com.romaneekang.boss.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作员表
 * @TableName pms_operator
 */
@TableName(value ="pms_operator")
@Data
public class PmsOperator implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "show_name")
    private String showName;

    /**
     * 手机号
     */
    @TableField(value = "mobile_no")
    private String mobileNo;

    /**
     * 登录账号
     */
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @TableField(value = "login_pwd")
    private String loginPwd;

    /**
     * 用户类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 密码盐值
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 状态(ENABLE, DISABLE)
     */
    @TableField(value = "status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}