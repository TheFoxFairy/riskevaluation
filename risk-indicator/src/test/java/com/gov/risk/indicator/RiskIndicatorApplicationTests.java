package com.gov.risk.indicator;

import com.gov.risk.indicator.utils.ExcelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootTest
class RiskIndicatorApplicationTests {

    @Autowired
    ExcelUtils excelUtils;
    @Test
    public void testExecel() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:指标体系（0919）.xlsx");
        excelUtils.run(file.getAbsoluteFile().toString());

    }

}
