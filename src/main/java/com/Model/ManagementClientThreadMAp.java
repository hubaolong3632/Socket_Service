package com.Model;

import com.服务器.server.SocketThreadService;

import java.util.HashMap;

public class ManagementClientThreadMAp {
    public  HashMap<String, SocketThreadService> map=new HashMap<>();

    public  void addThread(String key,SocketThreadService socket){
        map.put(key,socket);
    }

    public  SocketThreadService getThread(String user){
     return map.get(user); //返回线程
    }

}
