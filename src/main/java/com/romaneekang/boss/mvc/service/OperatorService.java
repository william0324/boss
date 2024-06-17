package com.romaneekang.boss.mvc.service;

import com.romaneekang.boss.mvc.model.dto.OperatorLoginResultDto;
import com.romaneekang.boss.mvc.model.form.OperatorLoginForm;

/**
 * 操作员业务
 */
public interface OperatorService {
    /**
     * 1. 验证登录用户是否有效
     * 2. 创建访问系统的jwt
     * 3. 存储jwt到redis
     * 4. 返回接口需要的数据(jwt,operatorId,showName)
     * @param operatorLoginForm 前端传来的数据
     * @return  返回给前端的数据
     */
    OperatorLoginResultDto operatorLogin(OperatorLoginForm operatorLoginForm);
}
