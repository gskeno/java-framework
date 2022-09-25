package com.gsonkeno.spring.bean;


/**
 * Created by gaosong on 2018-04-03
 */
public abstract class GetBeanTest {
    public void showMe(){
        this.getBean().showMe();
    }

    public abstract User getBean();

}
