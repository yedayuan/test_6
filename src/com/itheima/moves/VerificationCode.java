package com.itheima.moves;

import java.util.Random;

public class VerificationCode {
    //验证码
    public static final Random r = new Random();   //导包随机数

    /**验证码
     *
     * @param f 几位数
     * @return    随机数
     */
    public static String GetVerificationCode(int f){
        StringBuilder codes= new StringBuilder();
String code="abcdefghijklmnopqrstuvwsyzABCDEFGHIJKLMNOPQRSTUVWSYZ0123456789";
        for (int i = 0; i < f; i++) {
            char c = code.charAt(r.nextInt(code.length()));
           codes.append(c);

        }
        return codes.toString();
    }
}
