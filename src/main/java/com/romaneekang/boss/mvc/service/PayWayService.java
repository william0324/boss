package com.romaneekang.boss.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.domain.PayWay;
import com.romaneekang.boss.mvc.model.form.ConfigPayWayForm;
import com.romaneekang.boss.mvc.model.vo.DicItem;

import java.util.List;

public interface PayWayService {
    List<DicItem> getPayWayDicList();

    List<DicItem> getPayTypeDicList(String wayCode);

    int addProductPayWayConfig(ConfigPayWayForm configPayWayForm);

    IPage<PayWay> pageList(String productCode, Integer pageNo, Integer pageSize);

    void removePayWayConfig(String payWayId);
}
