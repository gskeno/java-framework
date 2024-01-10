package com.gson.design_pattern.proxy.jdk_multi_proxy;

import java.lang.reflect.Proxy;

public class JdkProxyMain {

    public static void main(String[] args) {
        // 本地生成代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 原始类
        StudyAboard studyAboard = new StudyAboard();

        // fatherWarnTravel是代理类，代理的是studyAboard
        Travel fatherWarnTravel = (Travel)Proxy.newProxyInstance(JdkProxyMain.class.getClassLoader(), new Class[]{Travel.class}, new FatherWarn(studyAboard));

        // motherWarnTravel是代理类，代理的是fatherWarnTravel
        Travel motherWarnTravel = (Travel) Proxy.newProxyInstance(JdkProxyMain.class.getClassLoader(), new Class[]{Travel.class}, new MotherWarn(fatherWarnTravel));

        motherWarnTravel.getExperience();
    }
}
