package com.romaneekang.boss.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.enums.buz.PublicStatus;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mapper.PayProductMapper;
import com.romaneekang.boss.mvc.model.form.ProductForm;
import com.romaneekang.boss.mvc.service.PayProductService;
import com.romaneekang.boss.utils.NoUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PayProductServiceImpl implements PayProductService {
    @Resource
    private PayProductMapper payProductMapper;
    @Override
    public IPage<PayProduct> payProductPageList(Integer pageNo, Integer pageSize) {
        return payProductMapper.selectPage(Page.of(pageNo,pageSize),new QueryWrapper<PayProduct>().orderByDesc("edit_time"));
    }

    @Override
    public void productAdd(ProductForm productForm) {
        // productCode productName 各自都是唯一的
        String productCode = productForm.getProductCode().trim();
        String productName = productForm.getProductName().trim();
        QueryWrapper<PayProduct> queryWrapper = new QueryWrapper<PayProduct>();
        queryWrapper.eq("product_code",productCode);
        if (payProductMapper.selectOne(queryWrapper) != null) {
            throw new BossException(Code.PRODUCT_CODE_EXIST);
        }
        QueryWrapper<PayProduct> queryWrapper1 = new QueryWrapper<PayProduct>();
        queryWrapper1.eq("product_name",productName);
        if (payProductMapper.selectOne(queryWrapper1) != null) {
            throw new BossException(Code.PRODUCT_NAME_EXIST);
        }
        // 创建新的产品记录
        PayProduct payProduct = new PayProduct();
        payProduct.setId(NoUtil.uuid());
        payProduct.setProductCode(productCode);
        payProduct.setProductName(productName);
        payProduct.setStatus(PublicStatus.DISABLE.name());
        payProduct.setEditTime(new Date());
        payProductMapper.insert(payProduct);
    }
}
