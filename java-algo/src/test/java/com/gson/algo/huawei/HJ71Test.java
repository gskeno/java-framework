package com.gson.algo.huawei;

import org.junit.Test;

public class HJ71Test {
    @Test
    public void test(){
        //System.out.println("aa".substring(2));
        System.out.println(HJ71字符串通配符.isMatch("aa","*"));
    }

    @Test
    public void test1(){
        System.out.println(HJ71字符串通配符.isMatch("aa","a"));
    }

    @Test
    public void test2(){
        System.out.println(HJ71字符串通配符.isMatch("cb","?a"));
    }

    @Test
    public void test3(){
        System.out.println(HJ71字符串通配符.isMatch("adceb","*a*b"));
    }

    @Test
    public void test4(){
        System.out.println(HJ71字符串通配符.isMatch("acdcb","a*c?b"));
        System.out.println(HJ71字符串通配符.isMatch("txt12.xls","te?t*.*"));
        System.out.println(HJ71字符串通配符.isMatch("zz","z"));
        System.out.println(HJ71字符串通配符.isMatch("pppq","pq"));
        System.out.println(HJ71字符串通配符.isMatch("0QZz","**Z"));
        System.out.println(HJ71字符串通配符.isMatch("abcd","?*Bc*?"));
        System.out.println(HJ71字符串通配符.isMatch("h#a","h*?*a"));
        System.out.println(HJ71字符串通配符.isMatch(
                "pppppppqppqqppqppppqqqppqppqpqqqppqpqpppqpppqpqqqpqqp",
                "p*p*qp**pq*p**p***ppq"));
    }

    @Test
    public void test5(){
        HJ71字符串通配符.dfs("acdcb","a*c?b");
        HJ71字符串通配符.dfs("txt12.xls","te?t*.*");
        HJ71字符串通配符.dfs("zz","z");
        HJ71字符串通配符.dfs("pppq","pq");
        HJ71字符串通配符.dfs("0QZz","**Z");
        HJ71字符串通配符.dfs("abcd","?*Bc*?");
        HJ71字符串通配符.dfs("h#a","h*?*a");
        HJ71字符串通配符.dfs("pppppppqppqqppqppppqqqppqppqpqqqppqpqpppqpppqpqqqpqqp",
                "p*p*qp**pq*p**p***ppq");
        HJ71字符串通配符.dfs("txt12.xls", "t?t*1*.*");

    }



}
