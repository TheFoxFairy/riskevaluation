package com.gov.risk.algorithms;

import com.gov.risk.algorithms.service.IndexesService;
import com.gov.risk.algorithms.utils.AlgorithmRelatedUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RiskAlgorithmsApplicationTests {

    @Autowired
    IndexesService indexesService;

    @Test
    void contextLoads() {
        AlgorithmRelatedUtils relatedUtils = new AlgorithmRelatedUtils();
//        HashMap<String,Object> options = relatedUtils.readYmlForOptions();
        relatedUtils.schedule("algorithm1");
    }

}
