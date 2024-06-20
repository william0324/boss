package com.romaneekang.boss.mvc.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo {
    private String id;
    /**
     * 账号
     */
    private String userNo;

    /**
     * 商户登录名称
     */
    private String loginName;

    /**
     * 资金账号
     */
    private String accountNo;

    /**
     * 联系手机号
     */
    private String phone;


    /**
     * 商户平台简称
     */
    private String shopName;

    /**
     * 经营范围
     */
    private String shopScope;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 状态(ENABLE, DISABLE)
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;
}
