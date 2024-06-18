package com.romaneekang.boss.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.romaneekang.boss.constants.KeyConstant;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.utils.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

/**
 * 验证jwt拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1."OPTIONS"预检请求,直接放行
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        // 2.从head中获取jwt
        String authorization = request.getHeader("Authorization");
        String operatorId = request.getHeader("operatorId");
        if (StrUtil.isNotBlank(authorization) && StrUtil.isNotBlank(operatorId) && authorization.startsWith("Bearer ")) {
            // Authorization:Bearer <JWT>
            String jwt = authorization.substring(7);
            // 从redis获取已经存储的jwt和operatorId
            String jwtKey = KeyConstant.OPERATOR_LOGIN + operatorId;
            String redisJsonToken = redisUtil.get(jwtKey);
            if (StrUtil.isNotBlank(redisJsonToken)) {
                JSONObject object = JSONUtil.parseObj(redisJsonToken);
                String saveJwt = object.getStr("jwt");
                if (StrUtil.isNotBlank(jwt) && jwtKey.equals(saveJwt)) {
                    // jwt有效的
                    return true;
                }
            }
        }
        // Jwt无效的,返回jwt无效的部分
        AjaxResult ajaxResult = AjaxResult.error(Code.OPERATOR_LOGIN_JWT_INVALID);
        String loginInvValidJson = JSONUtil.toJsonStr(ajaxResult);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(loginInvValidJson);
        writer.flush();
        writer.close();
        return false;
    }
}
