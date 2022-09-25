package com.gsonkeno.pattern.decorator;

/**
 * Created by gaosong on 2018-01-16
 */
public class DourthGradeSchoolReport extends SchoolReport {
    @Override
    public void report() {
        System.out.println("尊敬的XXX家长");

        System.out.println("您的孩子语文65，数学70");

        System.out.println("请签字:______\n");
    }

    @Override
    public void sign(String name) {
        System.out.println("签字:" + name);
    }
}
