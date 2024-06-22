package com.romaneekang.boss.mvc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.constants.BossConst;
import com.romaneekang.boss.convert.PayProductConvert;
import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;
import com.romaneekang.boss.mvc.model.form.ProductForm;
import com.romaneekang.boss.mvc.model.page.PageInfo;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import com.romaneekang.boss.mvc.service.PayProductService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PayProductController {

    @Resource
    private PayProductService payProductService;
    @Resource
    private PayProductConvert payProductConvert;

    /**
     * 分页查询商品信息。
     *
     * @param pageNo 请求的页码数。
     * @return 包含商品列表和分页信息的AjaxResult对象。
     */
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

    /**
     * 添加商品信息。
     * 通过@RequestBody注解，将前端发送的请求体绑定到ProductForm对象上，@Validated注解用于验证绑定的数据是否有效。
     * 使用@PostMapping注解指定该方法处理的HTTP请求方法为POST，请求路径为/product/add。
     *
     * @param productForm 商品信息表单，包含添加商品所需的所有信息。
     * @return 返回一个AjaxResult对象，表示操作结果。如果操作成功，返回OK表示。
     */
    @PostMapping("/product/add")
    public AjaxResult productAdd(@RequestBody @Validated ProductForm productForm) {
        payProductService.productAdd(productForm);
        return AjaxResult.OK();
    }


    @GetMapping("/product/dic")
    public AjaxResult productDic() {
        // TODO 缓存redis
        List<PayProduct> payProductList = payProductService.queryDicList();
        // 转为DicItem
        List<DicItem> dicItems = payProductConvert.payProductListToDicItemList(payProductList);
        return AjaxResult.OK(dicItems);
    }
}
