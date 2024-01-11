package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

import java.lang.reflect.Proxy;

public class ChainJdkMultiProxyMain {

    public static void main(String[] args) {
        // 本地生成代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 被代理类，原始类，代理目标类
        SwimStudy target = new SwimStudy();
        ProxyEntrance proxyEntrance = new ProxyEntrance(target);
        // 最外层教练，教蛙泳
        proxyEntrance.addInterceptor(new IntermediateCoach());
        // 内层教练，教狗刨
        proxyEntrance.addInterceptor(new JuniorCoach());
        // 动态生成的本地代码类
        Study study = (Study)Proxy.newProxyInstance(ChainJdkMultiProxyMain.class.getClassLoader(), new Class[]{Study.class}, proxyEntrance);
        study.learn();
    }
}
