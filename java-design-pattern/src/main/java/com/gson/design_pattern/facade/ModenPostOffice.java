package com.gsonkeno.pattern.facade;

/**
 * Created by gaosong on 2018-01-18
 */
public class ModenPostOffice {

    private ILetterProcess iLetterProcess = new LetterProcessImpl();

    public void sendLetter(String message, String address){
        iLetterProcess.writeContext(message);
        iLetterProcess.fillEnvelope(address);
        iLetterProcess.letterInEnvelope();
        iLetterProcess.sendLetter();
    }
}
