package com.gson.guava.math;

import com.google.common.math.DoubleMath;
import org.junit.Assert;
import org.junit.Test;

import java.math.RoundingMode;

public class MathTest {
    @Test
    public void test(){
        // 向+∞方向取整
        Assert.assertEquals(DoubleMath.roundToInt(127.502d, RoundingMode.CEILING), 128);
        Assert.assertEquals(DoubleMath.roundToInt(127.002d, RoundingMode.CEILING), 128);
        Assert.assertEquals(DoubleMath.roundToInt(-127.002d, RoundingMode.CEILING), -127);

        // 向-∞方向取整
        Assert.assertEquals(DoubleMath.roundToInt(-127.002d, RoundingMode.FLOOR), -128);
        // 向+∞, -∞ 方向取整
        Assert.assertEquals(DoubleMath.roundToInt(-127.002d, RoundingMode.UP), -128);
        Assert.assertEquals(DoubleMath.roundToInt(127.002d, RoundingMode.UP), 128);
        // 向 0 方向取整
        Assert.assertEquals(DoubleMath.roundToInt(-127.002d, RoundingMode.DOWN), -127);
        Assert.assertEquals(DoubleMath.roundToInt(127.002d, RoundingMode.DOWN), 127);
    }
}
