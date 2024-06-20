package com.romaneekang.boss.mvc.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 编辑商户的form
 */
@Data
public class UserEditForm {
    private String id;
    @NotBlank(message = "登录名称不能为空")
    @Size(min = 5,max = 20,message = "登录名称长度在{min}-{max}之间")
    private String loginName;
    @NotBlank(message = "手机号不能为空")
    @Pattern(
            regexp = "^1[3-9]\\d{9}$",
            message = "手机号格式不正确"
    )
    private String phone;
    @NotBlank(message = "交易平台简称不能为空")
    @Size(min = 5,max = 20,message = "商户名称长度在{min}-{max}之间")
    private String shopName;
    @NotBlank(message = "商户经营范围不能为空")
    @Size(min = 10,max = 200,message = "商户经营范围长度在{min}-{max}之间")
    private String shopScope;
    @NotBlank(message = "商户邮箱不能为空")
    @Pattern(
            regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",
            message = "邮箱格式不正确"
    )
    private String userEmail;
}
