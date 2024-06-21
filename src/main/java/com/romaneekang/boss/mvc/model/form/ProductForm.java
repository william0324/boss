package com.romaneekang.boss.mvc.model.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductForm {
    @NotBlank(message = "产品编码不能为空")
    private String productCode;
    @NotBlank(message = "产品名称不能为空")
    private String productName;
}
