package com.romaneekang.boss.mvc.controller;

import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.dto.OperatorLoginResultDto;
import com.romaneekang.boss.mvc.model.form.OperatorLoginForm;
import com.romaneekang.boss.mvc.model.menu.SubMenu;
import com.romaneekang.boss.mvc.service.OperatorService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public AjaxResult operatorMenus(@RequestHeader("operatorId") Long operatorId) {
        List<SubMenu> operatorMenus = operatorService.getOperatorMenus(operatorId);
        return AjaxResult.OK(operatorMenus);
    }
}
