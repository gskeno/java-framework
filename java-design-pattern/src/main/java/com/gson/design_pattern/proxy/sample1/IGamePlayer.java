package com.gsonkeno.pattern.proxy.sample1;

/**
 * Created by gaosong on 2018-01-22
 */
public interface IGamePlayer {
    public void login(String user, String password);

    public void killBoss();

    public void upgrade();

    public IGamePlayer getProxy();

}
