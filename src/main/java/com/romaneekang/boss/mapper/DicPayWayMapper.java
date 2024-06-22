package com.romaneekang.boss.mapper;

import com.romaneekang.boss.domain.DicPayWay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.romaneekang.boss.mvc.model.vo.DicItem;

import java.util.List;

/**
* @author weika
* @description 针对表【dic_pay_way】的数据库操作Mapper
* @createDate 2024-06-17 12:09:19
* @Entity com.romaneekang.boss.domain.DicPayWay
*/
public interface DicPayWayMapper extends BaseMapper<DicPayWay> {

    List<DicItem> selectDicList();
}




