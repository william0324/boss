package com.romaneekang.boss.mvc.service.impl;

import com.romaneekang.boss.mvc.model.menu.SubMenu;
import com.romaneekang.boss.mvc.service.OperatorService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OperatorServiceImplTest {

    @Resource
    private OperatorService operatorService;

    @Test
    void operatorLogin() {
    }

    @Test
    void getOperatorMenus() {
        List<SubMenu> operatorMenus = operatorService.getOperatorMenus(1L);
        assertNotNull(operatorMenus);
    }
}