package com.gov.risk.consumer.listener;

import com.gov.risk.consumer.utils.MsgUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Listener {
    MsgUtils msgUtils=MsgUtils.getInstance();

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("risk_strque"),
            exchange = @Exchange(value = "risk_direct",type = ExchangeTypes.DIRECT),
            key={"str"}
    ))
    public void listenDirectstrQueue(String msg) {
        msgUtils.AddStrMsgList(msg);
        System.out.println(msgUtils.StrMsgListIsEmp());
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("risk_jsonque"),
            exchange = @Exchange(value = "risk_direct",type = ExchangeTypes.DIRECT),
            key={"json"}
    ))
    public void listenDirectjsonQueue(Map<String,Object> map) {
        msgUtils.AddMapMsgList(map);
        System.out.println(msgUtils.StrMsgListIsEmp());
    }
}
