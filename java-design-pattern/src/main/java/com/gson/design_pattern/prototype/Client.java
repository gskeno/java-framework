package com.gsonkeno.pattern.prototype;

import java.util.Random;

/**
 * Created by gaosong on 2018-01-30
 */
public class Client {
    public static void main(String[] args) {

        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xxx银行版权所有");

        for (int i = 0; i < 100; i++) {
            Mail cloneMail = mail.clone();
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(7) + ".com");
            cloneMail.setAppellation("尊敬的" + getRandString(3) + "先生(女士)");
            sendMail(cloneMail);
        }
    }

    public static void sendMail(Mail mail){
        System.out.println( "标题:" + mail.getSubject() + "\t 收件人:" + mail.getReceiver() + "\t 发送成功");
    }

    public static String getRandString(int maxLength){
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();

        Random random = new Random();

        for (int i = 0; i < maxLength; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }
}
