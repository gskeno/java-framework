package com.gsonkeno.pattern.builder;

import java.util.ArrayList;

/**
 * Created by gaosong on 2018-01-19
 */
public class Director {
    private ArrayList<String> queue = new ArrayList<>();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    public BenzModel getABenzModel(){
        this.queue.clear();
        this.queue.add("start");
        this.queue.add("stop");

        this.benzBuilder.setSequeue(queue);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public BenzModel getBBenzModel(){
        this.queue.clear();
        this.queue.add("start");
        this.queue.add("alarm");

        this.benzBuilder.setSequeue(queue);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public BenzModel getCBMWModel(){
        this.queue.clear();
        this.queue.add("start");
        this.queue.add("alarm");
        this.queue.add("stop");

        this.bmwBuilder.setSequeue(queue);
        return (BenzModel) this.bmwBuilder.getCarModel();
    }


    public BenzModel getDBMWModel(){
        this.queue.clear();
        this.queue.add("start");
        this.queue.add("alarm");
        this.queue.add("engineBoom");
        this.queue.add("stop");

        this.bmwBuilder.setSequeue(queue);
        return (BenzModel) this.bmwBuilder.getCarModel();
    }

}
