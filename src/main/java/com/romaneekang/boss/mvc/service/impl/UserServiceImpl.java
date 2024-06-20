package com.romaneekang.boss.mvc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.romaneekang.boss.domain.UserAccount;
import com.romaneekang.boss.domain.UserInfo;
import com.romaneekang.boss.enums.ajax.Code;
import com.romaneekang.boss.enums.buz.PublicStatus;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mapper.UserAccountMapper;
import com.romaneekang.boss.mapper.UserInfoMapper;
import com.romaneekang.boss.mvc.model.form.UserEditForm;
import com.romaneekang.boss.mvc.service.UserService;
import com.romaneekang.boss.utils.NoUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserAccountMapper userAccountMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userEdit(UserEditForm userForm) {
        // 判断id是否为空,null新增，反之修改
        if (StrUtil.isBlank(userForm.getId())) { //新增user record
            // loginName不能重复
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("login_name", userForm.getLoginName());
            if (userInfoMapper.selectOne(queryWrapper) != null) {
                throw new BossException(Code.USER_LOGIN_NAME_EXIST);
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setId(NoUtil.uuid());
            userInfo.setUserNo(NoUtil.getUserNo());
            userInfo.setLoginName(userForm.getLoginName());
            userInfo.setAccountNo(NoUtil.getAccountNo());
            userInfo.setPhone(userForm.getPhone());
            userInfo.setPassword(NoUtil.uuid());
            userInfo.setPayPwd(NoUtil.uuid());
            userInfo.setShopName(userForm.getShopName());
            userInfo.setShopScope(userForm.getShopScope());
            userInfo.setUserEmail(userForm.getUserEmail());
            userInfo.setStatus(PublicStatus.ENABLE.name());
            userInfo.setCreateTime(new Date());
            userInfo.setRemark("创建商户");
            userInfoMapper.insert(userInfo);
            // 没创建一个用户就创建一个对应的资金账号
            UserAccount userAccount = new UserAccount();
            userAccount.setId(NoUtil.uuid());
            userAccount.setUserNo(userInfo.getUserNo());
            userAccount.setAccountNo(userInfo.getAccountNo());
            userAccount.setBalance(new BigDecimal(0));
            userAccount.setTotalIncome(new BigDecimal(0));
            userAccount.setTotalExpend(new BigDecimal(0));
            userAccount.setStatus(PublicStatus.ENABLE.name());
            userAccount.setRemark("创建资金账号");
            userAccount.setEditTime(new Date());
            userAccountMapper.insert(userAccount);
        } else {    // 修改 user
            UserInfo modifyUser = new UserInfo();
            modifyUser.setId(userForm.getId());
            modifyUser.setPhone(userForm.getPhone());
            modifyUser.setShopName(userForm.getShopName());
            modifyUser.setShopScope(userForm.getShopScope());
            modifyUser.setUserEmail(userForm.getUserEmail());
            userInfoMapper.updateById(modifyUser);
        }
    }

    @Override
    public IPage<UserInfo> userPageList(Integer pageNo, Integer pageSize) {
        return userInfoMapper.selectPage(Page.of(pageNo, pageSize),
                new QueryWrapper<UserInfo>().orderByDesc("create_time"));
    }

    @Override
    public UserInfo queryByUserNo(String userNo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no", userNo);
        return userInfoMapper.selectOne(queryWrapper);
    }
}
