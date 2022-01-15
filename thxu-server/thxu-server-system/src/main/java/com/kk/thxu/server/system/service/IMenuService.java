package com.kk.thxu.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kk.thxu.common.entity.router.VueRouter;
import com.kk.thxu.common.entity.system.Menu;

import java.util.List;
import java.util.Set;

public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户名查询用户权限信息
     *
     * @param username 用户名
     * @return 权限信息
     */
    Set<String> findUserPermissions(String username);

    /**
     * 通过用户名创建对应的 Vue路由信息
     *
     * @param username 用户名
     * @return 路由信息
     */
    List<VueRouter<Menu>> getUserRouters(String username);
}