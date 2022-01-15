package com.kk.thxu.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
//用户实体类  用于装载登录时，从t_user表里查询出来的数据

/**
 * getAuthorities获取用户包含的权限，返回权限集合，权限是一个继承了GrantedAuthority的对象；
 * <p>
 * getPassword和getUsername用于获取密码和用户名；
 * <p>
 * isAccountNonExpired方法返回boolean类型，用于判断账户是否未过期，未过期返回true反之返回false；
 * <p>
 * isAccountNonLocked方法用于判断账户是否未锁定；
 * <p>
 * isCredentialsNonExpired用于判断用户凭证是否没过期，即密码是否未过期；
 * <p>
 * isEnabled方法用于判断用户是否可用。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ThxuAuthUser extends User {

    private static final long serialVersionUID = 1805188637243336794L;
    private Long userId;

    private String avatar;

    private String email;

    private String mobile;

    private String sex;

    private Long deptId;

    private String deptName;

    private String roleId;

    private String roleName;

    private Date lastLoginTime;

    private String description;

    private String status;

    public ThxuAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ThxuAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
