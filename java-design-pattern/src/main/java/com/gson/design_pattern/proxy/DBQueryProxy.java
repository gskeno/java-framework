package com.gsonkeno.pattern.proxy;

/**
 * Created by gaosong on 2017-11-13
 */
public class DBQueryProxy implements IDBQuery {
    private  DBQuey query ;

    //初始化代理类时并不会初始化委托类，只有在实际调用request方法时才有可能初始化委托类
    @Override
    public String request() {
        if (query == null){
            query = new DBQuey();
        }
        return query.request();
    }
}
