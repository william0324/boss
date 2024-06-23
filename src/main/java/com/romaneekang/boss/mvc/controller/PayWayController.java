package com.romaneekang.boss.mvc.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.constants.BossConst;
import com.romaneekang.boss.convert.PayWayConvert;
import com.romaneekang.boss.domain.PayWay;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.form.ConfigPayWayForm;
import com.romaneekang.boss.mvc.model.page.PageInfo;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import com.romaneekang.boss.mvc.model.vo.PayWayVo;
import com.romaneekang.boss.mvc.service.PayWayService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payway")
public class PayWayController {
    @Resource
    public PayWayService payWayService;
    @Resource
    public PayWayConvert payWayConvert;

    @GetMapping("/way/dic")
    public AjaxResult payWayDic() {
        List<DicItem> payWayDicList = payWayService.getPayWayDicList();
        return AjaxResult.OK(payWayDicList);
    }

    @GetMapping("/type/dic")
    public AjaxResult payTypeDic(@RequestParam String wayCode) {
        if (StrUtil.isNotBlank(wayCode) && wayCode.trim().length() > 5) {
            List<DicItem> payTypeDicList = payWayService.getPayTypeDicList(wayCode.toUpperCase());
            return AjaxResult.OK(payTypeDicList);
        }
        return AjaxResult.error(Code.OPERATOR_PARAM_ERR);
    }

    @PostMapping("/config")
    public AjaxResult addProductPayWayConfig(@RequestBody @Validated ConfigPayWayForm configPayWayForm) {
        payWayService.addProductPayWayConfig(configPayWayForm);
        return AjaxResult.OK();
    }
    @GetMapping("/pageList")
    public AjaxResult pageList(@RequestParam(required = false) String productCode, @RequestParam Integer pageNo) {
        pageNo = (pageNo == null || pageNo < 1) ? 1 : pageNo;
        IPage<PayWay> pageList = payWayService.pageList(productCode, pageNo, BossConst.DEFAULT_PAGE_SIZE);
        // 返回数据进行处理
        // 列表数据
        List<PayWay> records = pageList.getRecords();
        List<PayWayVo> payWayVos = payWayConvert.payWayListToPayWayVoList(records);
        // 分页数据
        // 当前页码
        long current = pageList.getCurrent();
        // 总页数
        long totalPage = pageList.getPages();
        PageInfo pageInfo = new PageInfo(current, totalPage);
        Map<String, Object> result = Map.of("page", pageInfo, "list", payWayVos);
        return AjaxResult.OK(result);
    }

    @PostMapping("/config/remove")
    public AjaxResult removePayWayConfig(@RequestParam String payWayId) {
        if (StrUtil.isNotBlank(payWayId)) {
            payWayService.removePayWayConfig(payWayId);
            return AjaxResult.OK();
        }
        return AjaxResult.error(Code.OPERATOR_PARAM_ERR);
    }
}
