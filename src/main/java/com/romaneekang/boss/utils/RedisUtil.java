package com.romaneekang.boss.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    public void set(String key, String value) {
        // string类型，存储登录用户的数据，数据格式是json
        stringRedisTemplate.opsForValue().set(key, value);
    }
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
