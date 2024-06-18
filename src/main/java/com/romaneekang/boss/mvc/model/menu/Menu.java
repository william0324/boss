package com.romaneekang.boss.mvc.model.menu;

import lombok.Data;

/**
 * 菜单基本数据
 */
@Data
public class Menu {
    /**
     * 菜单id
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单编号M001-M001001
     */
    private String number;
    /**
     * 菜单链接
     */
    private String url;
    /**
     * 菜单是否为子菜单
     */
    private String leaf;
}
