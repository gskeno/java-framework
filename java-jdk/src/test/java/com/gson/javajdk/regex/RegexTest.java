package com.gson.javajdk.regex;

import org.junit.Test;

public class RegexTest {

    /**
     * 贪婪模式 默认
     * 独占模式 末尾加+
     * 懒惰模式 末尾加?
     * https://mp.weixin.qq.com/s?__biz=MzA4MjIyNTY0MQ==&mid=2647738965&idx=1&sn=61706e46fc7cf175ebc17fe5472f9f95&scene=21#wechat_redirect
     * @param args
     */
    public static void main(String[] args) {
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }

    /**
     * https://www.cnblogs.com/study-everyday/p/7426862.html
     */
    @Test
    public void test1(){
        // 独占模式(不回溯)
        String badRegex = "ab{1,3}+bc";
        // 贪婪模式(回溯)
        badRegex = "ab{1,3}bc";
        // 懒惰模式(回溯)
        badRegex = "ab{1,3}?bc";
        String bugUrl = "abbc";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }
    }
}
