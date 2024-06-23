package com.romaneekang.boss.convert;

import com.romaneekang.boss.domain.UserPayConfig;
import com.romaneekang.boss.mvc.model.vo.UserPayConfigVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommonConvert.class})
public interface UserPayConfigConvert {

    @Mappings({
            @Mapping(source = "payProductCode", target = "productCode"),
            @Mapping(source = "payProductName", target = "productName"),
            @Mapping(source = "editTime", target = "editTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "autoSett", target = "autoSett", qualifiedByName = "handleAutoSett")
    })
    UserPayConfigVo userPayConfigToUserPayConfigVo(UserPayConfig userPayConfig);

    List<UserPayConfigVo> userPayConfigListToUserPayConfigVoList(List<UserPayConfig> userPayConfigList);

}