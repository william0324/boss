package com.romaneekang.boss.mvc.controller;

import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.dto.OperatorLoginResultDto;
import com.romaneekang.boss.mvc.model.form.OperatorLoginForm;
import com.romaneekang.boss.mvc.service.OperatorService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作员Controller
 */
@RestController
public class OperatorController {

    @Resource
    private OperatorService operatorService;

    // 登录，获取jwt(token)
    @PostMapping("/operator/login")
    public AjaxResult operatorLogin(@RequestBody @Validated OperatorLoginForm operatorLoginForm) {
        OperatorLoginResultDto operatorLoginResultDto = operatorService.operatorLogin(operatorLoginForm);

        return AjaxResult.OK(operatorLoginResultDto);
    }
    // 菜单
    @GetMapping("/operator/menus")
    public AjaxResult operatorMenus() {
        System.out.println("/operator/menus");
        return AjaxResult.OK();
    }
}
