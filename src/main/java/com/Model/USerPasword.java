package com.Model;//package 服务器端.Model;

import java.io.Serializable;

public class USerPasword implements Serializable {
    private String name;
    private String pasword;
//客户端
    public USerPasword() {
    }

    public USerPasword(String name, String pasword) {
        this.name = name;
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
