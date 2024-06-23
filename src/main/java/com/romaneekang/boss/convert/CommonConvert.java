package com.romaneekang.boss.convert;

import com.romaneekang.boss.enums.buz.PublicStatus;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
@Component
public class CommonConvert {
    @Named("handleStatus")
    public String handleStatus(String status) {
        String res = "未知";
        try {
            PublicStatus publicStatus = PublicStatus.valueOf(status);
            res = publicStatus.getMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    @Named("handleAutoSett")
    public String handleAutoSett(String autoSett) {
        if ("YES".equals(autoSett)) {
            return "是";
        } else if ("NO".equals(autoSett)) {
            return "否";
        }
        return "未知";
    }
}