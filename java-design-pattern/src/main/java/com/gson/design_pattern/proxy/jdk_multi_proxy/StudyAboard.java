package com.gson.design_pattern.proxy.jdk_multi_proxy;

public class StudyAboard implements Travel{
    @Override
    public void getExperience() {
        System.out.println("出国旅行并学习,生活好苦，还缺钱");
    }
}
