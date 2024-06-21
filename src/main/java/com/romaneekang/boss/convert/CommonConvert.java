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
}