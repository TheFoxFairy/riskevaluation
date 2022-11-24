package com.gov.risk.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 权限表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Service
@FeignClient(name = "risk-sys")
public interface PowersService {
    @RequestMapping(value = "/sys/powers/getPowerAllRoles",method = RequestMethod.GET)
    HashMap<String, String> getPowerAllRoles();
}

