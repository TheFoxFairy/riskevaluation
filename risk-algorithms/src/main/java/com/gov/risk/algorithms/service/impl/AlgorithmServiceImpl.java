package com.gov.risk.algorithms.service.impl;

import com.gov.risk.algorithms.service.AlgorithmService;
import com.gov.risk.algorithms.service.IndexesService;
import com.gov.risk.algorithms.utils.AlgorithmRelatedUtils;
import com.gov.risk.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    @Autowired
    IndexesService indexesService;

    @Async("taskExecutor")
    @Override
    public R task() {


        // 调取算法工具
        AlgorithmRelatedUtils relatedUtils = new AlgorithmRelatedUtils();
        relatedUtils.schedule("algorithm1");

        return R.ok();
    }
}
