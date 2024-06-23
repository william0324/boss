package com.romaneekang.boss.mvc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.romaneekang.boss.PowerBossApplication;
import com.romaneekang.boss.domain.PayWay;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.enums.buz.PublicStatus;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mapper.PayWayMapper;
import com.romaneekang.boss.mvc.model.form.ConfigPayWayForm;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import com.romaneekang.boss.mvc.service.PayWayService;
import com.romaneekang.boss.utils.NoUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PayWayServiceImpl implements PayWayService {
    @Resource
    private PayWayMapper payWayMapper;
    @Override
    public List<DicItem> getPayWayDicList() {
        return PowerBossApplication.DIC_PAY_WAY_LIST;
    }

    @Override
    public List<DicItem> getPayTypeDicList(String wayCode) {
        List<DicItem> list = PowerBossApplication.DIC_PAY_TYPE_MAP.get(wayCode);
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public int addProductPayWayConfig(ConfigPayWayForm configPayWayForm) {
        // 1.producCode,paywaycode,paytypeCode 三者唯一
        QueryWrapper<PayWay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("way_code", configPayWayForm.getWayCode())
                .eq("pay_product_code", configPayWayForm.getProductCode())
                .eq("type_code", configPayWayForm.getTypeCode());
        PayWay payWay = payWayMapper.selectOne(queryWrapper);
        if (payWay != null) {
            throw new BossException(Code.PRODUCT_WAY_TYPE_EXIST);
        }
        // 添加记录
        payWay = new PayWay();
        payWay.setId(NoUtil.uuid());
        payWay.setWayCode(configPayWayForm.getWayCode());
        payWay.setWayName(PowerBossApplication.DIC_PAY_WAY_NAME_MAP.get(configPayWayForm.getWayCode()));
        payWay.setTypeCode(configPayWayForm.getTypeCode());
        String key = configPayWayForm.getWayCode() + ":" + configPayWayForm.getTypeCode();
        payWay.setTypeName(PowerBossApplication.DIC_PAY_TYPE_NAME_MAP.get(key));
        payWay.setPayProductCode(configPayWayForm.getProductCode());
        payWay.setPayRate(new BigDecimal(configPayWayForm.getPayRate())); // 设置支付费率为5%
        payWay.setSorts(Integer.valueOf(configPayWayForm.getSorts()));
        payWay.setStatus(PublicStatus.ENABLE.name());
        // 假设当前时间为修改时间
        payWay.setEditTime(new Date());
        payWay.setRemark("创建支付方式");
        return payWayMapper.insert(payWay);
    }

    @Override
    public IPage<PayWay> pageList(String productCode,Integer pageNo, Integer pageSize) {
        QueryWrapper<PayWay> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("pay_product_code", "way_code", "type_code");
        if (StrUtil.isNotBlank(productCode)) {
            queryWrapper.eq("pay_product_code", productCode);
        }
        return payWayMapper.selectPage(Page.of(pageNo, pageSize),queryWrapper);
    }
}
