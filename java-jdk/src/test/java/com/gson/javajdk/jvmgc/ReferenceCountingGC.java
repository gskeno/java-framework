package com.gson.javajdk.jvmgc;

/**
 *
 * vm options
 * -XX:+PrintGCDetails
 * @date 2021/1/30
 */
public class ReferenceCountingGC {
    static class Reference{
        Object reference;
    }

    public static void main(String[] args) {
        Reference reference1 = new Reference();
        Reference reference2 = new Reference();
        reference1.reference = reference2;
        reference2.reference = reference1;

        System.gc();
        System.out.println("------------");
        reference1 = null;
        reference2 = null;
        System.gc();
    }
}
