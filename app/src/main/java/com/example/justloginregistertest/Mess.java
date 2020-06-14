package com.example.justloginregistertest;

public class Mess {
    private String name;            //用户名
    private String mess;            //信息
    private String  time;

    public Mess(String name, String mess,String time) {
        this.name = name;
        this.mess = mess;
        this.time = time;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMess() {
        return mess;
    }
    public void setMess(String mess) {
        this.mess = mess;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", mess='" + mess + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
