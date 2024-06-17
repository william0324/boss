package com.romaneekang.boss.mvc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回给前端的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorLoginResultDto {
    private Long operatorId;
    private String jwt;
    private String showName;
}
