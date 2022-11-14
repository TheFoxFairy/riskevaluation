//package com.gov.risk.auth.modules.security.service;
//
//import com.gov.risk.auth.modules.sys.entity.UsersEntity;
//import com.gov.risk.auth.modules.sys.service.PowersService;
//import com.gov.risk.auth.modules.sys.service.UsersService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import sun.security.provider.PolicyParser;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class SecurityUserDetailService implements UserDetailsService {
//
//
//    @Autowired
//    private UsersService userService;
//
//    @Autowired
//    private PowersService permissionService;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//
//        UsersEntity user = userService.getUserByUsername(username);
//        if (user == null) {
//            return null;
//        }
//        //获取权限
//        List<PolicyParser.PermissionEntry> permissions = permissionService.getPermissionsByUserId(user.getId());
//        List<String> codes = permissions.stream().map(PermissionEntry::getCode).collect(Collectors.toList());
//        String[] authorities = null;
//        if (CollectionUtils.isNotEmpty(codes)) {
//            authorities = new String[codes.size()];
//            codes.toArray(authorities);
//        }
//        //身份令牌
//        String principal = JSON.toJSONString(user);
//        return User.withUsername(principal).password(user.getPassword()).authorities(authorities).build();
//    }
//}
