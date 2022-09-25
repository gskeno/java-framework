package com.gsonkeno.pattern.chainresponsibilty;

/**
 * Created by gaosong on 2018-01-16
 */
public class Client {
    public static void main(String[] args) {
        IHandler fatherHandler = new FatherHandler();
        IHandler husbandHandler = new HusbandHandler();
        IHandler sonHandler = new SonHandler();

        fatherHandler.setNextHandler(husbandHandler);
        husbandHandler.setNextHandler(sonHandler);

        IWomen women = new Women(3,"出去逛街");
        fatherHandler.HandleRequest(women);
    }
}
