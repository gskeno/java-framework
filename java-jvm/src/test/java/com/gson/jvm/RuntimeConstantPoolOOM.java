package com.gsonkeno.jdk.training.jvm;

/**
 *
 * 参考博客 :
 * https://blog.csdn.net/seu_calvin/article/details/52291082 的 2.2节 讲的比较清楚
 * Created by gaosong on 2018-03-25
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString(); //堆中创建实例
        System.out.println(str1.intern() == str1);//intern方法发现堆中已有该实例，便在常量池中存储该实例的地址

        String str3 = new String("语文"); //会在堆和常量池中都会创建 语文
        System.out.println(str3 == str3.intern());

        String str2 = new StringBuilder("flo").append("ats").toString();
        //String str2 = new StringBuilder("floats").toString();

        System.out.println(str2.intern() == str2);

        String s = new String("abc");
        String s1 = "abc";
        String s2 = new String( "abc");
        System.out.println(s == s1.intern());
        System.out.println(s == s2.intern());
        System.out.println(s1 == s.intern());
        System.out.println(s1 == s2.intern());
    }
}
