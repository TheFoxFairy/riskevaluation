package com.gov.risk.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RiskAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskAuthApplication.class, args);
    }

}
