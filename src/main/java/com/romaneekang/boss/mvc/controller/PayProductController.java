package com.romaneekang.boss.mvc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.constants.BossConst;
import com.romaneekang.boss.convert.PayProductConvert;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.page.PageInfo;
import com.romaneekang.boss.mvc.service.PayProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PayProductController {

    @Resource
    private PayProductService payProductService;
    @Resource
    private PayProductConvert payProductConvert;

    @GetMapping("/product/pageList")
    public AjaxResult pageList(@RequestParam Integer pageNo) {
        // 默认第一页
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        // 分页查询
        IPage<PayProduct> payProductIPage = payProductService.payProductPageList(pageNo, BossConst.DEFAULT_PAGE_SIZE);
        // 获得查询数据
        List<PayProduct> payProductList = payProductIPage.getRecords();
        // 获得当前页
        long currentPage = payProductIPage.getCurrent();
        // 获得总页数
        long totalPage = payProductIPage.getPages();
        // 封装数据
        PageInfo page = new PageInfo(currentPage, totalPage);
        Map<String, Object> info = Map.of("list", payProductConvert.payProductListToPayProductVoList(payProductList), "page", page);
        return AjaxResult.OK(info);
    }
}
