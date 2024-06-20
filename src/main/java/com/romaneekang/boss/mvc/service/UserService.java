package com.romaneekang.boss.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.domain.UserInfo;
import com.romaneekang.boss.exception.BossException;
import com.romaneekang.boss.mvc.model.form.UserEditForm;

public interface UserService {
    /**
     * 编辑用户信息。如果用户ID为空，则视为新增用户；否则视为更新用户信息。
     *
     * @param userForm 用户信息表单，包含用户要编辑的所有信息。
     * @throws BossException 如果登录名已存在，则抛出异常。
     */
    public void userEdit(UserEditForm userForm);

    /**
     * 根据页码和每页大小获取用户信息分页列表。<br>
     * 本方法通过调用userInfoMapper的selectPage方法，实现根据页码和每页大小查询用户信息的功能。<br>
     * 使用Page.of方法创建Page对象，该对象包含了分页信息，如当前页码和每页的大小。<br>
     * 调用QueryWrapper的orderByDesc方法设置查询条件，按照创建时间降序排序。<br>
     *
     * @param pageNo 当前页码，用于指定要查询的页。
     * @param pageSize 每页的大小，用于指定每页返回的记录数。
     * @return 返回IPage<UserInfo>对象，其中包含了分页查询的结果。
     */
    IPage<UserInfo> userPageList(Integer pageNo, Integer pageSize);
}
