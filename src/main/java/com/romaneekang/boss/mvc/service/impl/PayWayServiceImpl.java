package com.romaneekang.boss.mvc.service.impl;

import com.romaneekang.boss.PowerBossApplication;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import com.romaneekang.boss.mvc.service.PayWayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayWayServiceImpl implements PayWayService {
    @Override
    public List<DicItem> getPayWayDicList() {
        return PowerBossApplication.DIC_PAY_WAY_LIST;
    }
}
