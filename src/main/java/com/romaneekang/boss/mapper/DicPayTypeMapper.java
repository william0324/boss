package com.romaneekang.boss.mapper;

import com.romaneekang.boss.domain.DicPayType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.romaneekang.boss.domain.PayWay;
import com.romaneekang.boss.domain.result.PayTypeGroupContent;

import java.util.List;

/**
* @author weika
* @description 针对表【dic_pay_type】的数据库操作Mapper
* @createDate 2024-06-17 12:09:19
* @Entity com.romaneekang.boss.domain.DicPayType
*/
public interface DicPayTypeMapper extends BaseMapper<DicPayType> {
    List<PayTypeGroupContent> selectGroupPayWayContent();
}




