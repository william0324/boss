package com.romaneekang.boss.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.mapper.PayProductMapper;
import com.romaneekang.boss.mvc.service.PayProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PayProductServiceImpl implements PayProductService {
    @Resource
    private PayProductMapper payProductMapper;
    @Override
    public IPage<PayProduct> payProductPageList(Integer pageNo, Integer pageSize) {
        return payProductMapper.selectPage(Page.of(pageNo,pageSize),new QueryWrapper<PayProduct>().orderByDesc("edit_time"));
    }
}
