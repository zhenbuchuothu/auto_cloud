package com.kk.thxu.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.kk.thxu.common.entity.QueryRequest;
import com.kk.thxu.common.entity.ThxuResponse;
import com.kk.thxu.common.entity.system.SystemUser;
import com.kk.thxu.common.exception.ThxuException;
import com.kk.thxu.common.utils.ThxuUtil;
import com.kk.thxu.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')") //对方法进行了权限控制
    public ThxuResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = ThxuUtil.getDataTable(userService.findUserDetail(user, queryRequest));
        return new ThxuResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@Valid SystemUser user) throws ThxuException {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new ThxuException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws ThxuException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new ThxuException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws ThxuException, ThxuException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new ThxuException(message);
        }
    }
}