package com.gov.risk.indicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class RiskIndicatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskIndicatorApplication.class, args);
    }

}
