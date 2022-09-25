package com.gsonkeno.jdk.training.jvm;

/**
 * final关键字可以保证可见性
 * Created by gaosong on 2018-03-28
 */
public class FinalC {
    public static final int i ;

    public final int j;

    static {
        i = 1;
    }
    {
        j = 2;
    }

    public static void main(String[] args) {
        FinalC finalC = new FinalC();
        System.out.println(finalC.j);
    }
}
