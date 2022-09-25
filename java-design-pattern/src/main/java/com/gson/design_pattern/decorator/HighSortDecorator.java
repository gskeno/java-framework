package com.gsonkeno.pattern.decorator;

/**
 * Created by gaosong on 2018-01-16
 */
public class HighSortDecorator extends Decorator {

    public HighSortDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHighSort(){
        System.out.println("班级排名20，比上次上升10名\n");
    }
    @Override
    public void report() {
        super.report();
        reportHighSort();
    }
}
