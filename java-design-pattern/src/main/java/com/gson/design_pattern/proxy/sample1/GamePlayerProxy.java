package com.gsonkeno.pattern.proxy.sample1;

/**
 * Created by gaosong on 2018-01-22
 */
public class GamePlayerProxy implements IGamePlayer {

    private  IGamePlayer iGamePlayer;

    public GamePlayerProxy(IGamePlayer gamePlayer) {
        this.iGamePlayer = gamePlayer;
    }

    @Override
    public void login(String user, String password) {
        this.iGamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        this.iGamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.iGamePlayer.upgrade();
    }

    @Override
    public IGamePlayer getProxy() {
        return this;
    }
}
