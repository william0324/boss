package com.romaneekang.boss.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.domain.PayProduct;

public interface PayProductService {

    IPage<PayProduct> payProductPageList(Integer pageNo, Integer pageSize);
}
