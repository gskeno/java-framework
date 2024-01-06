package com.gson.spring.ops;

import com.gson.spring.AppBeans;

public class OpsService {
    private Opt opt;

    public OpsService(String accessKey){
        KeyCenterWrapper keyCenterWrapper = AppBeans.getBean("keyCenterWrapper");
        String decryptAccessKey = keyCenterWrapper.decrypt(accessKey, "testName");
        this.opt = null;
    }
}
