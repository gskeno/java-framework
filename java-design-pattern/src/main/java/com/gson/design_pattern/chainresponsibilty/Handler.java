package com.gsonkeno.pattern.chainresponsibilty;

/**
 * Created by gaosong on 2018-01-16
 */
public abstract class Handler implements IHandler {
    public static final int FATHER_LEVEL_REQUEST = 1;
    public static final int HUSBAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;
    //能处理的请求级别
    private int level;
    private IHandler nextHandler;

    public Handler(int level) {
        this.level = level;
    }

    @Override
    public void HandleRequest(IWomen women) {
        if (women.getType() == level){
            response(women);
        }else if (nextHandler != null){
            nextHandler.HandleRequest(women);
        }else {
            System.out.println("---没地方请示了，按不同意处理---");
        }
    }
    @Override
    public void setNextHandler(IHandler handler){
        this.nextHandler = handler;
    }

    protected abstract void response(IWomen women);
}
