package com.itheima.moves;


import java.io.Serializable;

/**
 * 业务
 * 商家 类
 */
public class Merchant extends User implements Serializable {

    private  String shopName;//店铺名
    private  String  address;//店铺地址

    public Merchant() {
    }

    public Merchant(String loginName, String userName, String passWord, char sex, String phone, double money) {
        super(loginName, userName, passWord, sex, phone, money);
    }

    public Merchant(String shopName, String address) {
        this.shopName = shopName;
        this.address = address;
    }

    public Merchant(String loginName, String userName, String passWord, char sex, String phone, double money, String shopName, String address) {
        super(loginName, userName, passWord, sex, phone, money);
        this.shopName = shopName;
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "merchant{" +
                "shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}



