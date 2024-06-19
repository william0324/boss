package com.romaneekang.boss.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.romaneekang.boss.constants.KeyConstant;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.menu.SubMenu;
import com.romaneekang.boss.utils.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.util.List;

@Component
public class MenuInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从head中获取operatorId,拼装成redis key
        String operatorId = request.getHeader("operatorId");
        String menuKey = KeyConstant.OPERATOR_MENUS + operatorId;
        String redisJsonMenu = redisUtil.get(menuKey);
        // 如果redis中没有数据，请求继续执行
        if (StrUtil.isBlank(redisJsonMenu)) {
            return true;
        }
        // 有数据，从redis中查询数据，返回给前端
        List<SubMenu> subMenuList = JSONUtil.toList(redisJsonMenu, SubMenu.class);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(AjaxResult.OK(subMenuList)));
        writer.flush();
        writer.close();
        return false;
    }
}
