package com.gov.risk.auth.service.impl;

import com.gov.risk.auth.constant.MessageConstant;
import com.gov.risk.auth.entity.dto.Client;
import com.gov.risk.auth.service.ClientService;
import com.gov.risk.auth.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import cn.hutool.core.collection.CollUtil;

/**
 * 客户端管理业务类
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

    private List<Client> clientList;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolesService rolesService;

    public ClientServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        String clientSecret = passwordEncoder.encode("123456");
        clientList = new ArrayList<>();

//        System.out.println("rolesService.listWithTree2():"+rolesService.listWithTree2());

        // 1、密码模式
        clientList.add(Client.builder()
                .clientId("client-app")
                .resourceIds("oauth2-resource")
                .secretRequire(false)
                .clientSecret(clientSecret)
                .scopeRequire(false)
                .scope("all")
                .authorizedGrantTypes("password,refresh_token")
                .authorities(rolesService.listWithTree2())
                .accessTokenValidity(3600)
                .refreshTokenValidity(86400).build());

//        clientList.add(Client.builder()
//                .clientId("client-app")
//                .resourceIds("oauth2-resource")
//                .secretRequire(false)
//                .clientSecret(clientSecret)
//                .scopeRequire(false)
//                .scope("all")
//                .authorizedGrantTypes("password,refresh_token")
//                .authorities(rolesService.listWithTree2())
//                .accessTokenValidity(3600)
//                .refreshTokenValidity(86400).build());

        // 2、授权码模式
        clientList.add(Client.builder()
                .clientId("client-app-2")
                .resourceIds("oauth2-resource2")
                .secretRequire(false)
                .clientSecret(clientSecret)
                .scopeRequire(false)
                .scope("all")
                .authorizedGrantTypes("authorization_code,refresh_token")
                .webServerRedirectUri("/")
                .authorities("CUSTOM")
                .accessTokenValidity(3600)
                .refreshTokenValidity(86400).build());
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        List<Client> findClientList = clientList.stream().filter(item -> item.getClientId().equals(clientId)).collect(Collectors.toList());
        if (CollUtil.isEmpty(findClientList)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessageConstant.NOT_FOUND_CLIENT);
        }
        return new ClientPrincipal(findClientList.get(0));
    }
}