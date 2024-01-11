package com.gson.design_pattern.proxy.chain_jdk_multi_proxy;

public interface Interceptor {

    /**
     * 方法增强处理，调用链的前进是靠
     * @see Invocation#forward() 推动的
     * @param invocation
     * @return
     */
    Object invoke(Invocation invocation);
}
