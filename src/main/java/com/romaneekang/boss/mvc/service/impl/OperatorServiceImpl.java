package com.romaneekang.boss.mvc.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.romaneekang.boss.constants.KeyConstant;
import com.romaneekang.boss.domain.PmsMenu;
import com.romaneekang.boss.domain.PmsOperator;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mapper.PmsMenuMapper;
import com.romaneekang.boss.mapper.PmsOperatorMapper;
import com.romaneekang.boss.mvc.model.dto.OperatorLoginResultDto;
import com.romaneekang.boss.mvc.model.form.OperatorLoginForm;
import com.romaneekang.boss.mvc.model.menu.Menu;
import com.romaneekang.boss.mvc.model.menu.MenuItem;
import com.romaneekang.boss.mvc.model.menu.SubMenu;
import com.romaneekang.boss.mvc.service.OperatorService;
import com.romaneekang.boss.utils.JwtUtil;
import com.romaneekang.boss.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class OperatorServiceImpl implements OperatorService {
    @Resource
    private PmsOperatorMapper operatorMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private PmsMenuMapper menuMapper;

    @Override
    public OperatorLoginResultDto operatorLogin(OperatorLoginForm operatorLoginForm) {
        // 登录验证
        QueryWrapper<PmsOperator> queryWrapper = new QueryWrapper<>();
        // login_name字段值是唯一的
        queryWrapper.eq("login_name", operatorLoginForm.getLoginName());
        PmsOperator pmsOperator = operatorMapper.selectOne(queryWrapper);
        if (pmsOperator == null) {
            // 无此登录用户
            throw new BossException(Code.OPERATOR_LOGIN_ERR);
        }
        // 2.判断登录密码
        /*
         * 数据库中的登录密码经过md5加密生成，此处使用hutool工具的md5加密算法
         */
        String pwd = DigestUtil.md5Hex(operatorLoginForm.getLoginPwd() + pmsOperator.getSalt());
        if (!pwd.equals(pmsOperator.getLoginPwd())) {
            // 密码错误
            throw new BossException(Code.OPERATOR_LOGIN_ERR);
        }
        // 3.分配jwt
        // 创建一个新的 JSONObject 实例，用于存储键值对形式的 JSON 数据。
        JSONObject object = new JSONObject();
        // 向 JSONObject 实例添加一个键值对，其中键为 "operatorId"，值为 pmsOperator.getId() 的返回值。
        object.set("operatorId", pmsOperator.getId());
        // 将 JSONObject 实例转换为 JSON 字符串,0 是方法的参数，表示输出的 JSON 字符串是否包含空格缩进。当参数为 0 时，输出的 JSON 字符串是没有缩进的紧凑格式。
        String jwt = JwtUtil.createJwt(object.toJSONString(0));

        // 4.存储jwt到redis
        JSONObject user = new JSONObject();
        user.set("operatorId", pmsOperator.getId());
        user.set("jwt", jwt);
        user.set("loginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        String loginKey = KeyConstant.OPERATOR_LOGIN + pmsOperator.getId();
        // 参数 0 表示设置缩进级别为 0,即不添加任何缩进。
        redisUtil.set(loginKey, user.toJSONString(0));
        // 判断redis存储是否出现异常
        String loginJsonValue = redisUtil.get(loginKey);
        if (!StringUtils.hasText(loginJsonValue)) {
            throw new BossException(Code.OPERATOR_LOGIN_REDIS_ERR);
        }
        // 5.返回dto对象
        return new OperatorLoginResultDto(pmsOperator.getId(), jwt, pmsOperator.getLoginName());
    }

    @Override
    public List<SubMenu> getOperatorMenus(Long operatorId) {
        // 查询用户的菜单(submenu和menuitem都有的菜单数据结合)
        List<PmsMenu> pmsMenus = menuMapper.selectMenuByOperator(operatorId);
        // 将List<PmsMenu>分组,把number前4位相同的作为一个List集合,每个list集合是一个完整的SubMenu和MenuItem
        Map<String, List<PmsMenu>> listMap = new HashMap<>();
        for (PmsMenu pmsMenu : pmsMenus) {
            // number前四位
            String substringNumber = pmsMenu.getNumber().substring(0, 4);
            // map集合中不存在前四位的number,就新创建一个键值对，key是number前四位,value是空的list集合，用来存放前四位相同的pmsMenu
            if (!listMap.containsKey(substringNumber)) {
                listMap.put(substringNumber, new ArrayList<>());
            }
            // 将前四位number值相等的pmsMenu放到一个list集合中
            listMap.get(substringNumber).add(pmsMenu);
        }
        // 将Map中的List集合整理为List<SubMenu> SubMenu中的items是List<menuItem>
        List<SubMenu> subMenus = new ArrayList<>(); //顶层菜单集合
        listMap.forEach((key, value) -> {
            SubMenu subMenu = new SubMenu();    // 顶层菜单,每个key对应一个顶层菜单
            List<MenuItem> menuItems = new ArrayList<>();   //底层菜单集合，每个顶层菜单拥有一个底层菜单集合
            value.forEach(v -> {
                if (key.equals(v.getNumber())) { // 顶级菜单
                    // 给顶级菜单的五个属性赋值
                    subMenu.setId(v.getId());
                    subMenu.setLeaf(v.getIsLeaf());
                    subMenu.setName(v.getName());
                    subMenu.setUrl(v.getUrl());
                    subMenu.setNumber(v.getNumber());
                } else {    //子菜单,顶级菜单中的一个List集合
                    // 给最底层的菜单项赋值
                    MenuItem menuItem = new MenuItem(); //一个底层菜单
                    menuItem.setId(v.getId());
                    menuItem.setLeaf(v.getIsLeaf());
                    menuItem.setName(v.getName());
                    menuItem.setUrl(v.getUrl());
                    menuItem.setNumber(v.getNumber());
                    // 底层菜单存放到属于顶层菜单一个属性的list集合中
                    menuItems.add(menuItem);
                }
            });
            // 底层菜单集合交给顶层菜单
            subMenu.setItems(menuItems);
            // 顶层菜单形成一个list集合
            subMenus.add(subMenu);
        });
        // 生成菜单并保存到redis
        List<SubMenu> subMenuList = subMenus.stream().sorted(Comparator.comparing(Menu::getNumber)).toList();
        String jsonStr = JSONUtil.toJsonStr(subMenuList);
        redisUtil.set(KeyConstant.OPERATOR_MENUS + operatorId, jsonStr);
        return subMenuList;
    }
}
