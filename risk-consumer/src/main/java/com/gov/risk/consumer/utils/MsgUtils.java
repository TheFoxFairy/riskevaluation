package com.gov.risk.consumer.utils;
import java.util.ArrayList;
import java.util.Map;

public class MsgUtils {
    private ArrayList<String> StrMsgList;
    private ArrayList<Map> MapMsgList;
    private static MsgUtils instance;
    private MsgUtils(){
        MapMsgList=new ArrayList<>();
        StrMsgList=new ArrayList<>();
    }

    public static synchronized MsgUtils getInstance() {
        if (instance == null) {
            instance = new MsgUtils();
        }
        return instance;
    }
    public void AddStrMsgList(String msg){
        this.StrMsgList.add(msg);
    }
    public void AddMapMsgList(Map msg){
        this.MapMsgList.add(msg);
    }
    public ArrayList<String> GetStrMsgList(){
        return this.StrMsgList;
    }
    public ArrayList<Map> GetMapMsgList(){
        return this.MapMsgList;
    }
    public boolean MapMsgListIsEmp(){
        if(this.MapMsgList.size()>0){
            return false;
        }
        else {return true;}
    }
    public boolean StrMsgListIsEmp(){
        if(this.StrMsgList.size()>0){
            return false;
        }
        else {return true;}
    }
}
