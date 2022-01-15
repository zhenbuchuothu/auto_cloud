package com.kk.thxu.auth.service;

import com.kk.thxu.auth.manager.UserManager;
import com.kk.thxu.common.entity.ThxuAuthUser;
import com.kk.thxu.common.entity.system.SystemUser;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//校验用户名和密码
@Service
public class ThxuUserDetailService implements UserDetailsService {

    //ThxuSecurityConfigure中的
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
                notLocked = true;
            ThxuAuthUser authUser = new ThxuAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }

//    private ThxuAuthUser transSystemUserToAuthUser(ThxuAuthUser authUser, SystemUser systemUser) {
//        authUser.setAvatar(systemUser.getAvatar());
//        authUser.setDeptId(systemUser.getDeptId());
//        authUser.setDeptName(systemUser.getDeptName());
//        authUser.setEmail(systemUser.getEmail());
//        authUser.setMobile(systemUser.getMobile());
//        authUser.setRoleId(systemUser.getRoleId());
//        authUser.setRoleName(systemUser.getRoleName());
//        authUser.setSex(systemUser.getSex());
//        authUser.setUserId(systemUser.getUserId());
//        authUser.setLastLoginTime(systemUser.getLastLoginTime());
//        authUser.setDescription(systemUser.getDescription());
//        authUser.setStatus(systemUser.getStatus());
//        return authUser;
//    }
}
