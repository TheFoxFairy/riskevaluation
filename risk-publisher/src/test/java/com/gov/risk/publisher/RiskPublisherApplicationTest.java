package com.gov.risk.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskPublisherApplicationTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSenddirectExchangestr() {
        String exchangeName = "risk_direct";
        String message = "123456789";
        rabbitTemplate.convertAndSend(exchangeName, "str", message);
    }
    @Test
    public void testSenddirectExchangejson() throws InterruptedException {
        Map<String,Object>msg=new HashMap<>();
        msg.put("name","qq");
        msg.put("class","bb");
        String exchangeName = "risk_direct";
        rabbitTemplate.convertAndSend(exchangeName, "json", msg);
    }
}
