package com.romaneekang.boss.convert;


import com.romaneekang.boss.domain.UserInfo;
import com.romaneekang.boss.enums.buz.PublicStatus;
import com.romaneekang.boss.mvc.model.vo.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConvert {

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "status", target = "status", qualifiedByName = "handleStatus")
    })
    UserInfoVo userInfoToUserInfoVo(UserInfo userInfo);

    List<UserInfoVo> userInfoListToUserInfoVoList(List<UserInfo> userInfoList);

    @Named("handleStatus")
    default String handleStatus(String status) {
        String res = "未知";
        try {
            PublicStatus publicStatus = PublicStatus.valueOf(status);
            res = publicStatus.getMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
