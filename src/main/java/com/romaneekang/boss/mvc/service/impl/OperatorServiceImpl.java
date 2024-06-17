package com.romaneekang.boss.mvc.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.romaneekang.boss.constants.KeyConstant;
import com.romaneekang.boss.domain.PmsOperator;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mapper.PmsOperatorMapper;
import com.romaneekang.boss.mvc.model.dto.OperatorLoginResultDto;
import com.romaneekang.boss.mvc.model.form.OperatorLoginForm;
import com.romaneekang.boss.mvc.service.OperatorService;
import com.romaneekang.boss.utils.JwtUtil;
import com.romaneekang.boss.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Resource
    private PmsOperatorMapper operatorMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public OperatorLoginResultDto operatorLogin(OperatorLoginForm operatorLoginForm) {
        // 登录验证
        QueryWrapper<PmsOperator> queryWrapper = new QueryWrapper<>();
        // login_name字段值是唯一的
        queryWrapper.eq("login_name", operatorLoginForm.getLoginName());
        PmsOperator pmsOperator = operatorMapper.selectOne(queryWrapper);
        if (pmsOperator == null) {
            // 无此登录用户
            throw new BossException(Code.OPERATOR_LOGIN_ERR);
        }
        // 2.判断登录密码
        /*
         * 数据库中的登录密码经过md5加密生成，此处使用hutool工具的md5加密算法
         */
        String pwd = DigestUtil.md5Hex(operatorLoginForm.getLoginPwd() + pmsOperator.getSalt());
        if (!pwd.equals(pmsOperator.getLoginPwd())) {
            // 密码错误
            throw new BossException(Code.OPERATOR_LOGIN_ERR);
        }
        // 3.分配jwt
        // 创建一个新的 JSONObject 实例，用于存储键值对形式的 JSON 数据。
        JSONObject object = new JSONObject();
        // 向 JSONObject 实例添加一个键值对，其中键为 "operatorId"，值为 pmsOperator.getId() 的返回值。
        object.set("operatorId", pmsOperator.getId());
        // 将 JSONObject 实例转换为 JSON 字符串,0 是方法的参数，表示输出的 JSON 字符串是否包含空格缩进。当参数为 0 时，输出的 JSON 字符串是没有缩进的紧凑格式。
        String jwt = JwtUtil.createJwt(object.toJSONString(0));

        // 4.存储jwt到redis
        JSONObject user = new JSONObject();
        user.set("operatorId", pmsOperator.getId());
        user.set("jwt",jwt);
        user.set("loginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        String loginKey = KeyConstant.OPERATOR_LOGIN + pmsOperator.getId();
        redisUtil.set(loginKey,user.toJSONString(0));
        // 判断redis存储是否出现异常
        String loginJsonValue = redisUtil.get(loginKey);
        if (!StringUtils.hasText(loginJsonValue)) {
            throw new BossException(Code.OPERATOR_LOGIN_REDIS_ERR);
        }
        // 5.返回dto对象
        return new OperatorLoginResultDto(pmsOperator.getId(), jwt ,pmsOperator.getLoginName());
    }
}
