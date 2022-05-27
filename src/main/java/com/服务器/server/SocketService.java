package com.服务器.server;



import com.Model.ManagementClientThreadMAp;
import com.服务器.IFwqService.MessageType;
import com.Model.Message;
import com.Model.USerPasword;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketService {
    ServerSocket socket;
    private ConcurrentMap<String,USerPasword> map=new ConcurrentHashMap<>(); //是一个线程安全的类


    private boolean checkUser(USerPasword user ){
        USerPasword uSerPasword = map.get(user.getName());
        if(uSerPasword==null){ //判断是否为空
            return false;
        }
        if(!uSerPasword.getPasword().equals(user.getPasword())){ //判断密码是否正确
            return false;
        }
        return true;


    }

   public SocketService(){

       map.put("1",new USerPasword("1","1"));
       map.put("2",new USerPasword("2","2"));
       map.put("3",new USerPasword("3","3"));


        System.out.println("服务器的 99999端口");
       ManagementClientThreadMAp map=new ManagementClientThreadMAp(); //用来保存线程
       try {
           socket=new ServerSocket(9999);

           while (true){
               try {
                   Socket accept = socket.accept();  //获取socket对象
                   ObjectInputStream ops = new ObjectInputStream(accept.getInputStream());

//                   System.out.println(ops.readObject());

                   USerPasword user = (USerPasword) ops.readObject(); //获取传输过来的类
                   //判断密码是否正确
                   Message message=new Message();

                   ObjectOutputStream oos=new ObjectOutputStream(accept.getOutputStream()); //传输过去
                   if(checkUser(user)){ //判断是否登录成功
                       System.out.println(user.getName()+"-->登录成功");
                       message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED); //登录成功
                       oos.writeObject(message); //传输回去线程成功


                       SocketThreadService socthread = new SocketThreadService(user.getName(),accept);
                       socthread.start(); //启动该线程

                       map.addThread(user.getName(),socthread); //保存线程

                   }else{
                       System.out.println(user.getName()+"->登录失败");
                       message.setMesType(MessageType.MESSAGE_LOGIN_FAIL); //登录失败
                       oos.writeObject(message);

                       accept.close(); //登录失败就关闭操作

                   }


                   } catch (IOException e) {
                       e.printStackTrace();
                   } catch (ClassNotFoundException e) {
                       e.printStackTrace();
                   }


           }
       } catch (IOException e) {
           e.printStackTrace();
       }

    }
}
