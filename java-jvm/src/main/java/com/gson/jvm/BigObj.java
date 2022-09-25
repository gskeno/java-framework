package com.gsonkeno.jdk.training.jvm;

/**
 * Created by gaosong on 2017-11-30
 */
public class BigObj {

    private String id;
    public BigObj(String id) {
        // 占用空间,让线程进行回收
        byte[] b = new byte[2 * 1024*1024];

        System.out.println("创建了" + id);
        this.id = id;
    }

    public String getId(){
        return id;
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("移除了" + id);
    }
}
