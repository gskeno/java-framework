package com.gson.javajdk.clazzloader;

public class TestMyCl extends  TestAbstractCl{

    public TestMyCl() {
        System.out.println("TestMyCl constructor initialized "
                + "is " + this.getClass().getClassLoader() + "\n"
                +  "hashcode is " + this.getClass().getClassLoader().hashCode()
        );
    }

    @Override
    public void sayHi() {
        System.out.println("TestMyCl sayHi "
                + "is " + this.getClass().getClassLoader() + "\n"
                +  "hashcode is " + this.getClass().getClassLoader().hashCode()
        );
    }
}