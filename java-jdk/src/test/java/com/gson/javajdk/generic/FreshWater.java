package com.gson.javajdk.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 淡水
 */
public class FreshWater extends Water{


    public static void main(String[] args) {
        FreshWater  water1 = new FreshWater();
        water1.setSaltPercentage(1);

        FreshWater  water2 = new FreshWater();
        water2.setSaltPercentage(2);

        List<FreshWater> waterList = new ArrayList<>();
        waterList.add(water2);
        waterList.add(water1);

        Collections.sort(waterList);
        System.out.println(waterList);
        // 编译异常
        // CollectionUtils.sort(waterList);
    }
}
