package com.gmail.deamon999;

public class User {
    private String info;
    private String phone;

    public User(String info, String phone) {
        this.info = info;
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "info: " + info + ", phone: " + phone;
    }
}
