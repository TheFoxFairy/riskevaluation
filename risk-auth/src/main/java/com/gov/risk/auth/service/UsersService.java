package com.gov.risk.auth.service;

import com.gov.risk.auth.entity.entity.UsersEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 用户表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Service
@FeignClient(name = "risk-sys")
public interface UsersService{
    @RequestMapping(value = "/sys/users/listWithTree",method = RequestMethod.GET)
    List<UsersEntity> listWithTree(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/sys/users/listWithTree2",method = RequestMethod.GET)
    HashMap<String, CopyOnWriteArraySet<Object>> listWithTree2(@RequestParam Map<String, Object> params);
}

