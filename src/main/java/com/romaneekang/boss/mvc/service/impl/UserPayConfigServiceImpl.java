package com.romaneekang.boss.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.romaneekang.boss.domain.UserPayConfig;
import com.romaneekang.boss.mapper.UserPayConfigMapper;
import com.romaneekang.boss.mvc.service.UserPayConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserPayConfigServiceImpl implements UserPayConfigService {
    @Resource
    private UserPayConfigMapper userPayConfigMapper;
    @Override
    public IPage<UserPayConfig> pageList(Integer pageNo, Integer pageSize) {
        QueryWrapper<UserPayConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("edit_time");
        return userPayConfigMapper.selectPage(Page.of(pageNo,pageSize), queryWrapper);
    }
}
