package com.gsonkeno.pattern.proxy.sample1;

/**
 * Created by gaosong on 2018-01-22
 */
public class Client {

    public static void main(String[] args) {
        GamePlayer gamePlayer = new GamePlayer("张三");

        gamePlayer.login("zhangsan","878s");
        gamePlayer.killBoss();
        gamePlayer.upgrade();

        System.out.print("\n");

        GamePlayerProxy playerProxy = new GamePlayerProxy(new GamePlayer("张三"));
        playerProxy.login("zhangsan","878s");
        playerProxy.killBoss();
        playerProxy.upgrade();

        System.out.print("\n");
        GamePlayer gamePlayer1 = new GamePlayer("张三");
        IGamePlayer proxy = gamePlayer1.getProxy();
        proxy.login("zhangsan","787s");
        proxy.killBoss();
        proxy.upgrade();

    }
}
