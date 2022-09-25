package com.gsonkeno.pattern.chainresponsibilty;

/**
 * Created by gaosong on 2018-01-16
 */
public interface IHandler {
    public void HandleRequest(IWomen women);

    public void setNextHandler(IHandler handler);
}
