package com.romaneekang.boss.convert;


import com.romaneekang.boss.domain.UserInfo;
import com.romaneekang.boss.enums.buz.PublicStatus;
import com.romaneekang.boss.mvc.model.vo.QueryUserInfoVo;
import com.romaneekang.boss.mvc.model.vo.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CommonConvert.class})
public interface UserConvert {

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "status", target = "status", qualifiedByName = "handleStatus")
    })
    UserInfoVo userInfoToUserInfoVo(UserInfo userInfo);

    QueryUserInfoVo userInfoToQueryUserInfoVo(UserInfo userInfo);

    List<UserInfoVo> userInfoListToUserInfoVoList(List<UserInfo> userInfoList);

}
