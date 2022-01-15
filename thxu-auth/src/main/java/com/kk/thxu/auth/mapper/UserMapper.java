package com.kk.thxu.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kk.thxu.common.entity.system.SystemUser;

public interface UserMapper extends BaseMapper<SystemUser> {

    SystemUser findByName(String username);
}