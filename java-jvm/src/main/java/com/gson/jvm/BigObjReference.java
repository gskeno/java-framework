package com.gsonkeno.jdk.training.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 自定义的若引用
 * Created by gaosong on 2017-11-30
 */
public class BigObjReference extends WeakReference<BigObj> {
    private String id;
    public BigObjReference(BigObj referent, ReferenceQueue<? super BigObj> q) {
        super(referent, q);
        this.id = referent.getId();
    }

    public String getId(){
        return this.id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("移除了若引用->" + getId());
    }
}
