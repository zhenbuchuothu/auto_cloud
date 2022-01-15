package com.kk.thxu.auth.manager;

import com.kk.thxu.auth.mapper.MenuMapper;
import com.kk.thxu.auth.mapper.UserMapper;
import com.kk.thxu.common.entity.system.Menu;
import com.kk.thxu.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于统一定义和用户相关的业务方法 :对应数据表t_user数据，主要用于用户的CRUD等；
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    /**
     *      Java 8的Stream简化
     *     public String findUserPermissions(String username) {
     *         List<Menu> userPermissions = menuMapper.findUserPermissions(username);
     *         return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
     *     }
     */
    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        List<String> perms = new ArrayList<>();
        for (Menu m: userPermissions){
            perms.add(m.getPerms());
        }
        return StringUtils.join(perms, ",");
    }
}