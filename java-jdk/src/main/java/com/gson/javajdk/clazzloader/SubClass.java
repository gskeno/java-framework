package com.gson.javajdk.clazzloader;

public class SubClass extends SuperClass{
    static {
        System.out.println("SuperClass static init");
    }

    @Override
    protected void detailOnRender(){
        System.out.println("子类渲染时需要看局部细节");
    }



    @Override
    public void decoratePage(){
        super.decoratePage();
    }
}
