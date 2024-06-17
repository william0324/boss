package com.romaneekang.boss.mvc.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 接收前端传过来的json数据
 */
@Data
public class OperatorLoginForm {
    @NotBlank(message = "登录的名称是必须的")
    @Size(min = 5,max = 20,message = "登录名称字符长度在{min}-{max}")
    private String loginName;
    @NotBlank(message = "登录密码是必须的")
    @Size(min = 32,max = 32,message = "登录密码长度不符合要求")
    private String loginPwd;
}
