package com.romaneekang.boss.mvc.model.ajax;

import com.romaneekang.boss.enums.ajax.Code;
import lombok.Data;

/**
 * 统一应答结果
 */
@Data
public class AjaxResult {
    private Integer code;
    private String msg;
    private Object info;
    public static AjaxResult OK(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(Code.SUCCESS.getCode());
        result.setMsg(Code.SUCCESS.getMessage());
        result.setInfo(data);
        return result;
    }
    public static AjaxResult OK() {
        return OK(null);
    }
    public static AjaxResult error(Code code) {
        AjaxResult result = new AjaxResult();
        result.setCode(code.getCode());
        result.setMsg(code.getMessage());
        result.setInfo(null);
        return result;
    }
}
