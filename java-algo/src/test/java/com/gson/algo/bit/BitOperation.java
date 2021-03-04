package com.gson.algo.bit;

import org.junit.Test;

public class BitOperation {
    @Test
    public void test(){
        int a = 0b1000_0000_0000_0000_0000_0000_0000_0000;
        System.out.println(a);

        int val = -2147483648; // input data
        int ans = 0;
        while (val != 0) {
            int tmp = val % 2;
            if (tmp == 1) ++ans;
            val /= 2;
            System.out.println("val=" + val);
        }


        System.out.println("ans=" + ans);
        //--------------
        System.out.println(-1%2);

    }
}
