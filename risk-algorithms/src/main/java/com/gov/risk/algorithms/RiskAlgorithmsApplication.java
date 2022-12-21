package com.gov.risk.algorithms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
public class RiskAlgorithmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskAlgorithmsApplication.class, args);
    }

}
