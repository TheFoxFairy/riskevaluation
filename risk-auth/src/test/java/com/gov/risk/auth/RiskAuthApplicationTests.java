package com.gov.risk.auth;

import jdk.internal.dynalink.support.NameCodec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class RiskAuthApplicationTests {

//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Test
//    void contextLoads() {
//        String clientSecret = passwordEncoder.encode("123456");
//        System.out.println(clientSecret);
//    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        String clientSecret = passwordEncoder.encode("123456");
        System.out.println(clientSecret);
    }

}
