package com.romaneekang.boss.exception;

import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.mvc.model.ajax.AjaxResult;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class BossExceptionHandle {
    @ExceptionHandler({BossException.class})
    public AjaxResult bossException(BossException e) {
        Code code = e.getCode();
        return AjaxResult.error(e.getCode());
    }

    // 处理BeanValidation异常
    @ExceptionHandler({BindException.class})
    public AjaxResult bindException(BindException e) {
        StringBuilder builder = new StringBuilder();
        e.getFieldErrors().forEach(fieldError -> {
            builder.append("输入项[");
            builder.append(fieldError.getField());
            builder.append("],值错误[");
            builder.append(fieldError.getDefaultMessage());
            builder.append("], ");
        });
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(Code.OPERATOR_PARAM_ERR.getCode());
        ajaxResult.setMsg(Code.OPERATOR_PARAM_ERR.getMessage() + ":" + builder.toString());
        ajaxResult.setInfo(null);
        return ajaxResult;
    }
}
