package com.gsonkeno.pattern.facade;

/**
 * Created by gaosong on 2018-01-18
 */
public class LetterProcessImpl implements  ILetterProcess{
    @Override
    public void writeContext(String message) {
        System.out.println("填写信的内容:" + message);
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人姓名和地址:" + address);
    }

    @Override
    public void letterInEnvelope() {
        System.out.println("把信写到信封中");
    }

    @Override
    public void sendLetter() {
        System.out.println("邮递信件");
    }
}
