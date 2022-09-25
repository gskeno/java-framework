package com.gsonkeno.pattern.facade;

/**
 * 写信过程
 * Created by gaosong on 2018-01-18
 */
public interface ILetterProcess {

    public void writeContext(String message);

    public void fillEnvelope(String address);

    public void letterInEnvelope();

    public void sendLetter();
}
