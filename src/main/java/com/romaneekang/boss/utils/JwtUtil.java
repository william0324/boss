package com.romaneekang.boss.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 */
public class JwtUtil {

    public static final String JWT_SECRET = "dhasohdiuasbd/adskjas)";
    /**
     * 创建jwt
     */
    public static String createJwt(String json) {
        // 组装头部信息
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create()
                // 头部
                .withHeader(header)
                // 负载,自定义数据
                .withClaim("user",json)
                // 签名：前面需要指定一个密匙
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }
}
