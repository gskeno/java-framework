package com.gsonkeno.pattern.chainresponsibilty;

/**
 * Created by gaosong on 2018-01-16
 */
public class FatherHandler extends Handler {

    public FatherHandler() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("---女儿向父亲请示---");
        System.out.println(women.getRequest());
        System.out.println("---父亲的答复是同意---\n");
    }
}
