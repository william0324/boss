package com.romaneekang.boss.mvc.model.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private long currentPage;
    private long totalPage;
}
