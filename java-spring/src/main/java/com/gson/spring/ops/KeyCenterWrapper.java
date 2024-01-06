package com.gson.spring.ops;

import org.springframework.beans.factory.annotation.Autowired;

public class KeyCenterWrapper {
    @Autowired
    Cryptograph cryptograph;

    String decrypt(String encryptedText, String keyName){
        KeyProcessor keyProcessor = cryptograph.getKeyStore().getKeyProcessor();
        if (keyProcessor == null){
            throw new RuntimeException("keyProcessor is null");
        }
        return null;
    }
}
