package com.romaneekang.boss.convert;

import com.romaneekang.boss.domain.PayWay;
import com.romaneekang.boss.mvc.model.vo.PayWayVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommonConvert.class})
public interface PayWayConvert {
    @Mappings({
            @Mapping(source = "payProductCode",target = "productCode"),
            @Mapping(source = "editTime", target = "editTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "status", target = "status", qualifiedByName = "handleStatus")
    })
    PayWayVo payWayToPayWayVo(PayWay payWay);

    List<PayWayVo> payWayListToPayWayVoList(List<PayWay> payWayList);
}
