package com.romaneekang.boss.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.mvc.model.form.ProductForm;

import java.util.List;

public interface PayProductService {

    IPage<PayProduct> payProductPageList(Integer pageNo, Integer pageSize);

    void productAdd(ProductForm productForm);

    /**
     * 查询支付产品字典列表。
     * 本方法通过调用payProductMapper的selectList方法，查询PayProduct表中所有产品的代码和名称，
     * 并按照产品代码的升序进行排序。这样做的目的是为了提供一个支付产品的列表供前端选择，
     * 列表包含了每个产品的唯一标识和名称，方便用户识别和选择。
     *
     * @return List<PayProduct> 返回一个支付产品列表，列表项包括产品代码和产品名称。
     */
    List<PayProduct> queryDicList();
}
