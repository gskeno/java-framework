package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

public interface Invocation {

    /**
     * 推动调用链向前执行
     * @return
     */
     Object forward();
}
