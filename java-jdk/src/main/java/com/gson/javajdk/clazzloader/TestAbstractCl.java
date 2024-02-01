package com.gson.javajdk.clazzloader;

public class TestAbstractCl {
    public TestAbstractCl() {
        System.out.println("TestAbstractCl constructor initialized "
                + "is " + this.getClass().getClassLoader() + "\n"
                +  "hashcode is " + this.getClass().getClassLoader().hashCode()

        );
    }

    public void sayHi(){
        System.out.println("TestAbstractCl sayHi "
                + "is " + this.getClass().getClassLoader() + "\n"
                +  "hashcode is " + this.getClass().getClassLoader().hashCode()
        );
    }
}
