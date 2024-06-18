package com.romaneekang.boss.mvc.model.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * SubMenu拥有MenuItem菜单项
 */
@Setter
@Getter
public class SubMenu extends Menu {
    private List<MenuItem> items;

}
