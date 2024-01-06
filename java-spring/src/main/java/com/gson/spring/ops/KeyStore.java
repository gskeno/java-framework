package com.gson.spring.ops;

public class KeyStore {
    private KeyProcessor keyProcessor;

    public void init(){
        try {
            Thread.sleep(30000);
            keyProcessor = new KeyProcessor();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public KeyProcessor getKeyProcessor() {
        return keyProcessor;
    }


}
