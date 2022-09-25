package com.gsonkeno.jdk.training.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Created by gaosong on 2017-11-30
 */
public class SpecifiSoftReference extends SoftReference<String> {

    public SpecifiSoftReference(String referent, ReferenceQueue q) {
        super(referent, q);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("自定义软引用被回收");
    }
}
