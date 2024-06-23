package com.romaneekang.boss.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.romaneekang.boss.domain.UserPayConfig;

public interface UserPayConfigService {
    IPage<UserPayConfig> pageList(Integer pageNo, Integer pageSize);
}
