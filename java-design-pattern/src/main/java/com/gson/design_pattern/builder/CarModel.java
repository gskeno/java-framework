package com.gsonkeno.pattern.builder;

import java.util.ArrayList;

/**
 * Created by gaosong on 2018-01-19
 */
public abstract class CarModel {
    private ArrayList<String> sequeue = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    final void run(){

        for (int i = 0; i < sequeue.size(); i++) {
            String actionName = sequeue.get(i);

            if (actionName.equals("start")){
                start();
            }else if (actionName.equals("stop")){
                stop();
            }else if (actionName.equals("alarm")){
                alarm();
            }else if (actionName.equals("engineBoom")){
                engineBoom();
            }
        }
    }

    final void setSequeue(ArrayList<String> sequeue){
        this.sequeue = sequeue;
    }


}
