package com.romaneekang.boss.mvc.controller;

import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import com.romaneekang.boss.mvc.service.PayWayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payway")
public class PayWayController {
    @Resource
    public PayWayService payWayService;
    @GetMapping("/way/dic")
    public AjaxResult payWayDic() {
        List<DicItem> payWayDicList = payWayService.getPayWayDicList();
        return AjaxResult.OK(payWayDicList);
    }
}
