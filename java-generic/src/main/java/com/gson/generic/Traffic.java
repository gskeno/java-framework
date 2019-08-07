package com.gson.generic;

/**
 * 交通工具
 * @param <T>
 */
public interface Traffic<T>{

    public void travel(T t);
}
