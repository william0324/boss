package com.romaneekang.boss.mvc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.constants.BossConst;
import com.romaneekang.boss.convert.UserPayConfigConvert;
import com.romaneekang.boss.domain.UserPayConfig;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.page.PageInfo;
import com.romaneekang.boss.mvc.model.vo.UserPayConfigVo;
import com.romaneekang.boss.mvc.service.UserPayConfigService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserPayConfigController {
    @Resource
    private UserPayConfigService userPayConfigService;
    @Resource
    private UserPayConfigConvert userPayConfigConvert;

    @GetMapping("/payconfig/pageList")
    public AjaxResult pageList(@RequestParam Integer pageNo) {
        pageNo = (pageNo == null || pageNo < 1) ? 1 : pageNo;
        IPage<UserPayConfig> pageResult = userPayConfigService.pageList(pageNo, BossConst.DEFAULT_PAGE_SIZE);
        List<UserPayConfig> records = pageResult.getRecords();
        List<UserPayConfigVo> userPayConfigVos = userPayConfigConvert.userPayConfigListToUserPayConfigVoList(records);
        long current = pageResult.getCurrent();
        long pages = pageResult.getPages();
        PageInfo pageInfo = new PageInfo(current, pages);
        Map<String, Object> result = Map.of("page", pageInfo, "list", userPayConfigVos);
        return AjaxResult.OK(result);
    }
}
