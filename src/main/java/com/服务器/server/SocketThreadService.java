package com.服务器.server;

import com.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketThreadService extends Thread {
private Socket socket;
private String user;

   public SocketThreadService(String user,Socket socket){
       this.socket=socket;
       this.user=user;
   }


    @Override
    public void run() {  //用来读取客户端发送过来的值
        System.out.println("服务端读取数据("+user+").....>");
        try {

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message mes =(Message)ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        return socket;
    }


    public String getUser() {
        return user;
    }
}
