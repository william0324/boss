package com.romaneekang.boss.mapper;

import com.romaneekang.boss.domain.PmsMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author weika
* @description 针对表【pms_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-06-17 12:09:19
* @Entity com.romaneekang.boss.domain.PmsMenu
*/
public interface PmsMenuMapper extends BaseMapper<PmsMenu> {
    List<PmsMenu> selectMenuByOperator(Long pid);
}




