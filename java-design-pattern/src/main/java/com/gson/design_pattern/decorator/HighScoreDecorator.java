package com.gsonkeno.pattern.decorator;

/**
 * Created by gaosong on 2018-01-16
 */
public class HighScoreDecorator extends Decorator {

    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHighScore(){
        System.out.println("考试语文最高分75，数学最高分80\n");
    }
    @Override
    public void report() {
        reportHighScore();
        super.report();
    }

}
