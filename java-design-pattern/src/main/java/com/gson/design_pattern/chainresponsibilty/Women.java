package com.gsonkeno.pattern.chainresponsibilty;

/**
 * Created by gaosong on 2018-01-16
 */
public class Women implements IWomen {
    /**
     * 状态:
     * 1.未出嫁
     * 2.出嫁
     * 3.夫死
     */
    private int type;

    /**
     * 请求内容
     */
    private String request;

    public Women(int type, String request) {
        this.type = type;
        switch (type){
            case 1:
                this.request = "女儿的请求是:" + request;
                break;
            case 2:
                this.request = "妻子的请求是:" + request;
                break;
            case 3:
                this.request = "母亲的请求是:" + request;
                break;
        }
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getRequest() {
        return request;
    }
}
