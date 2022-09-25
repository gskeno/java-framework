package com.gsonkeno.pattern.decorator;

/**
 * Created by gaosong on 2018-01-16
 */
public class Client {

    public static void main(String[] args) {
            SchoolReport sr = new DourthGradeSchoolReport();
            sr = new HighScoreDecorator(sr);
            sr = new HighSortDecorator(sr);

            sr.report();
            sr.sign("老五");
    }
}
