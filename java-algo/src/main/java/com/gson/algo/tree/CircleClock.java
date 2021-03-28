package com.gson.algo.tree;

/**
 * 圆环,0,1,2,....9,0
 * 每次都只能顺时针，或者逆时针走一步，
 * 求经过n步从0回到0的方法数
 */
public class CircleClock {

    /**
     * 经过nSteps步回到原点的方式总数
     * @param nSteps
     * @return
     */
    static int getStrategyCounts(int nSteps){
        //负数或者奇数直接返回0
        if (nSteps <0 || (nSteps&1) > 0){
            return 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        // getStrategyCounts(2) == 2;
        // getStrategyCounts(4) == 3;
        // 六步走法 0-1-2-3-2-1-0
        //        0-9-8-7-8-9-0
        //        0-1-2-1-2-1-0
        //        0-9-8-9-8-9-0
        //        0-1-0-1-2-1-0
        //        0-9-0-9-8-9-0
        // getStrategyCounts(6) == 3;
    }
}
