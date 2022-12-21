package com.gov.risk.consumer.controller;

import com.gov.risk.consumer.listener.Listener;
import com.gov.risk.consumer.utils.MsgUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/sys/sysmsg")
public class SysMsgController {
    MsgUtils msgUtils=MsgUtils.getInstance();
    @ResponseBody
    @RequestMapping("/str")
    public ArrayList<String> SysMsgStr(){
        return msgUtils.GetStrMsgList();
    }
    @RequestMapping("/json")
    public ArrayList<Map> SysMsgJson(){
        return msgUtils.GetMapMsgList();
    }

}
