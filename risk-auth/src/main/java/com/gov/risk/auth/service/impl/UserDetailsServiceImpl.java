package com.gov.risk.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.gov.risk.auth.constant.MessageConstant;
import com.gov.risk.auth.entity.entity.User;
import com.gov.risk.auth.service.UserDetailsService;
import com.gov.risk.auth.entity.entity.UsersEntity;
import com.gov.risk.auth.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户管理业务类
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UsersService usersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String, Object> params = new HashMap<>();
        params.put("userName",username);
        List<UsersEntity> findUserList  = usersService.listWithTree(params);

        if (CollUtil.isEmpty(findUserList)) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        User user = new User();
        UsersEntity usersEntity = findUserList.get(0);
        user.setId(usersEntity.getUserId());
        user.setUsername(usersEntity.getUserName());
        user.setPassword(usersEntity.getPassword());
        user.setStatus(usersEntity.getStatus());
        user.setRoles(usersEntity.getAllRolesName());

        UserPrincipal userPrincipal = new UserPrincipal(user);

        System.out.println(userPrincipal);

        if (!userPrincipal.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!userPrincipal.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!userPrincipal.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!userPrincipal.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return userPrincipal;
    }

}