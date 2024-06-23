package com.romaneekang.boss.mvc.model.form;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConfigPayWayForm {
    @NotBlank(message = "支付费率不能为空")
    @DecimalMin(value = "0.1", message = "支付费率不能小于0.1")
    @DecimalMax(value = "3", message = "支付费率不能大于3")
    private String payRate;
    @NotBlank(message = "产品编码不能为空")
    private String productCode;
    @NotBlank(message = "排序不能为空")
    @Min(value = 1, message = "排序从一开始")
    private String sorts;
    @NotBlank(message = "支付类型编码不能为空")
    private String typeCode;
    @NotBlank(message = "支付方式编码不能为空")
    private String wayCode;
}
