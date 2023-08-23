package com.itheima.moves;

import java.io.Serializable;

/*用户类*/
public  class User implements Runnable ,Serializable{
    private  static final long VersionNumber=1;// 版本号
    private String loginName;//登录名假名
    private String userName;//用户名
    private transient String passWord;//密码
    private char sex;//性别
    private String phone;//电话
    private transient double money;//账户余额

    public User() {
    }

    public User(String loginName, String userName, String passWord, char sex, String phone, double money) {
        this.loginName = loginName;
        this.userName = userName;
        this.passWord = passWord;
        this.sex = sex;
        this.phone = phone;
        this.money = money;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUseName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+"线执行");
    }

    @Override
    public String toString() {
        return "Uset{" +
                "loginName='" + loginName + '\'' +
                ", usetName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                '}';
    }
}
