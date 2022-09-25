package com.gsonkeno.pattern.proxy.sample1;

/**
 * Created by gaosong on 2018-01-22
 */
public class GamePlayer implements IGamePlayer {

    private IGamePlayer proxy;

    private String name;

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        if (proxy == null){
            System.out.println("请使用指定的代理访问");
        }else {
            System.out.println("用户" + user + ",名称" + password + "登录成功");
        }
    }

    @Override
    public void killBoss() {
        if (proxy == null){
            System.out.println("请使用指定的代理访问");
        }else {
            System.out.println(this.name + "在打怪");
        }
    }

    @Override
    public void upgrade() {
        if (proxy == null){
            System.out.println("请使用指定的代理访问");
        }else {
            System.out.println(this.name + "在升级");
        }
    }

    @Override
    public IGamePlayer getProxy() {
        this.proxy = new GamePlayerProxy(this);
        return this.proxy;
    }
}
