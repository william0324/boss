package com.romaneekang.boss.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.domain.PayWay;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.enums.buz.PublicStatus;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mapper.PayProductMapper;
import com.romaneekang.boss.mapper.PayWayMapper;
import com.romaneekang.boss.mvc.model.form.ProductForm;
import com.romaneekang.boss.mvc.service.PayProductService;
import com.romaneekang.boss.utils.NoUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PayProductServiceImpl implements PayProductService {
    @Resource
    private PayProductMapper payProductMapper;
    @Resource
    private PayWayMapper payWayMapper;

    @Override
    public IPage<PayProduct> payProductPageList(Integer pageNo, Integer pageSize) {
        return payProductMapper.selectPage(Page.of(pageNo, pageSize), new QueryWrapper<PayProduct>().orderByDesc("edit_time"));
    }

    @Override
    public void productAdd(ProductForm productForm) {
        // productCode productName 各自都是唯一的
        String productCode = productForm.getProductCode().trim();
        String productName = productForm.getProductName().trim();
        QueryWrapper<PayProduct> queryWrapper = new QueryWrapper<PayProduct>();
        queryWrapper.eq("product_code", productCode);
        if (payProductMapper.selectOne(queryWrapper) != null) {
            throw new BossException(Code.PRODUCT_CODE_EXIST);
        }
        QueryWrapper<PayProduct> queryWrapper1 = new QueryWrapper<PayProduct>();
        queryWrapper1.eq("product_name", productName);
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

    @Override
    public List<PayProduct> queryDicList() {
        return payProductMapper.selectList(new QueryWrapper<PayProduct>()
                .select("product_code", "product_name")
                .orderByAsc("product_code"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void productRemove(String productCode) {
         // 删除产品
        UpdateWrapper<PayProduct> updateWrapper = new UpdateWrapper<PayProduct>();
        updateWrapper.eq("product_code", productCode);
        int delete = payProductMapper.delete(updateWrapper);
        if (delete == 0) {
            throw new BossException(Code.PRODUCT_NOT_EXIST);
        }
        // 删除产品对应的支付方式
        payWayMapper.delete(new QueryWrapper<PayWay>().eq("pay_product_code", productCode));
    }

    @Override
    public void editStatus(String productCode) {
        // 先查看产品有没有
        PayProduct payProduct = payProductMapper.selectOne(new QueryWrapper<PayProduct>().eq("product_code", productCode));
        if (payProduct == null) {
            throw new BossException(Code.PRODUCT_NOT_EXIST);
        }
        String newStatus = PublicStatus.ENABLE.name();
        if (payProduct.getStatus().equals(PublicStatus.ENABLE.name())) {
            newStatus = PublicStatus.DISABLE.name();
        }
        UpdateWrapper<PayProduct> updateWrapper = new UpdateWrapper<PayProduct>();
        updateWrapper.eq("product_code", productCode);
        updateWrapper.set("status", newStatus);
        payProductMapper.update(updateWrapper);
    }
}
