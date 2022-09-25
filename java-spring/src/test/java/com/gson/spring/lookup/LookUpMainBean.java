package com.gson.spring.lookup;


/**
 * Created by gaosong on 2018-04-03
 */
public abstract class LookUpMainBean {
    public void showMe(){
        this.getUser().showMe();
    }

    public abstract LookUpUser getUser();

}
