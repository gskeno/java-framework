package com.gsonkeno.pattern.chainresponsibilty;

/**
 * Created by gaosong on 2018-01-16
 */
public class SonHandler extends Handler {

    public SonHandler() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("---母亲向儿子请示---");
        System.out.println(women.getRequest());
        System.out.println("---儿子的答复是同意---\n");
    }
}
