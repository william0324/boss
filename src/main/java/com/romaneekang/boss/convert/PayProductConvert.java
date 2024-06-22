package com.romaneekang.boss.convert;

import com.romaneekang.boss.domain.PayProduct;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import com.romaneekang.boss.mvc.model.vo.PayProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommonConvert.class})
public interface PayProductConvert {
    @Mappings({
            @Mapping(source = "editTime", target = "editTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "status", target = "status", qualifiedByName = "handleStatus")
    })
    PayProductVo payProductToPayProductVo(PayProduct payProduct);

    List<PayProductVo> payProductListToPayProductVoList(List<PayProduct> payProductList);

    @Mappings({
            @Mapping(source = "productCode", target = "code"),
            @Mapping(source = "productName", target = "label")
    })
    DicItem payProductToDicItem(PayProduct payProduct);

    List<DicItem> payProductListToDicItemList(List<PayProduct> payProductList);
}
