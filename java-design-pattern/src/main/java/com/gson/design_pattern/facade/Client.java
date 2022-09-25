package com.gsonkeno.pattern.facade;

/**
 * Created by gaosong on 2018-01-18
 */
public class Client {
    public static void main(String[] args) {
        ModenPostOffice office = new ModenPostOffice();

        String message = "请让我重生吧";
        String address = "天堂路 66号 大神官";

        //门面模式
        office.sendLetter(message, address);
    }
}
