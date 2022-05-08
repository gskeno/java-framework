package com.gson.algo.huawei;

import org.junit.Test;

public class HJ华为机考Test {

    @Test
    public void test(){
        String maxStr;
        maxStr = 华为机考.getMaxStr("1234567890abcd9.+12345.678.9ed");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("1234.");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("1.23.456.789");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("1.+23.456.+789.121");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("1.+23.456.+789.121+");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("+");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("-");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("-1");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("-1 +23");
        System.out.println(maxStr);

        maxStr = 华为机考.getMaxStr("8981-12.67");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr("+2+3-1.92+ 01");
        System.out.println(maxStr);
        //+12345.678
        //1234
        //456.789
        //+789.121
        //+789.121
        //
        //
        //-1
        //+23
        //-12.67
        //-1.92
    }

    @Test
    public void test1(){
        String maxStr;
        maxStr = 华为机考.getMaxStr1("1234567890abcd9.+12345.678.9ed");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("1234.");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("1.23.456.789");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("1.+23.456.+789.121");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("1.+23.456.+789.121+");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("+");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("-");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("-1");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("-1 +23");
        System.out.println(maxStr);

        maxStr = 华为机考.getMaxStr1("8981-12.67");
        System.out.println(maxStr);
        maxStr = 华为机考.getMaxStr1("+2+3-1.92+ 01");
        System.out.println(maxStr);
        // +12345.678
        //1234
        //456.789
        //+789.121
        //+789.121
        //
        //
        //-1
        //+23
        //-12.67
        //-1.92
    }

}
