package com.gov.risk.auth.service;

import com.gov.risk.auth.entity.entity.RolesEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author ThrFoxFairy
 * @email onlytokimeki@gmail.com
 * @date 2022-11-10 20:52:47
 */
@Service
@FeignClient(name = "risk-sys")
public interface RolesService{

    @RequestMapping(value = "/sys/roles/listWithTree",method = RequestMethod.GET)
    List<RolesEntity> listWithTree(@RequestParam Map<String, Object> params);
    @RequestMapping(value = "/sys/roles/listWithTree2",method = RequestMethod.GET)
    String listWithTree2();
}

