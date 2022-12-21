package com.gov.risk.algorithms.controller;

import com.gov.risk.algorithms.service.AlgorithmService;
import com.gov.risk.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/algorithms")
public class AlgorithmController {

    @Autowired
    AlgorithmService algorithmService;

    /**
     * 调用该task执行相应算法，数据是通过后台自己爬取的，不需要传递
     * @return
     */

    @RequestMapping("/task")
    public R task(){
        return algorithmService.task();
    }



}
