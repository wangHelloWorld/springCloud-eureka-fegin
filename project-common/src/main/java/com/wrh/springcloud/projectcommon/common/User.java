package com.wrh.springcloud.projectcommon.common;

public class User {
    private String name;
    private String password;

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public String toString(){
        return "name is "+ name +" password is "+password;

    }
}
