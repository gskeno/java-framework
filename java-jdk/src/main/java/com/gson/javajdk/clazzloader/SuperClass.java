package com.gson.javajdk.clazzloader;

public class SuperClass {
    static {
        System.out.println("SuperClass static init");
    }
    public static String ABC = "abc";

    protected void detailOnRender(){
        System.out.println("父类渲染时需要看整体面貌");
    }

    public void decoratePage(){
        System.out.println("父类渲染页面");
        detailOnRender();
    }
}
