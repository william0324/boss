package com.romaneekang.boss.mvc.service;

import com.romaneekang.boss.mvc.model.dto.OperatorLoginResultDto;
import com.romaneekang.boss.mvc.model.form.OperatorLoginForm;
import com.romaneekang.boss.mvc.model.menu.SubMenu;

import java.util.List;

/**
 * 操作员业务
 */
public interface OperatorService {
    /**
     * 1. 验证登录用户是否有效<br>
     * 2. 创建访问系统的jwt<br>
     * 3. 存储jwt到redis<br>
     * 4. 返回接口需要的数据(jwt,operatorId,showName)<br>
     * @param operatorLoginForm 前端传来的数据
     * @return  返回给前端的数据
     */
    OperatorLoginResultDto operatorLogin(OperatorLoginForm operatorLoginForm);

    /**
     * 查询当前用户的菜单
     * @param operatorId 用户id
     * @return 菜单集合
     */
    List<SubMenu> getOperatorMenus(Long operatorId);
}
