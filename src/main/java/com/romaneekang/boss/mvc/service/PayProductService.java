package com.romaneekang.boss.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.mvc.model.form.ProductForm;

public interface PayProductService {

    IPage<PayProduct> payProductPageList(Integer pageNo, Integer pageSize);

    void productAdd(ProductForm productForm);
}
