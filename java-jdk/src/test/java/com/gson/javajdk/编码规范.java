package com.gson.javajdk;

import org.junit.Test;

public class 编码规范 {

    @Test
    public void test3目运算符(){
        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        Boolean flag = false;
        // 表示式1和表达式2中有一个为基本类型，则两个表达式都要进行拆箱
        // 即a.intValue() * b.intValue();
        // c.intValue()
        Integer result = (flag ? a * b : c );
    }
}
