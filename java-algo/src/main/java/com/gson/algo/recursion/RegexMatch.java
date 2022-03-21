package com.gson.algo.recursion;

/**
 *
 * https://www.nowcoder.com/practice/28970c15befb4ff3a264189087b99ad4
 * 正则表达式匹配
 * *表示0个或者多个
 * .表示任意字符
 */
public class RegexMatch {
    public boolean match(String str, String pattern) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        // str的首位与pattern的首位是否匹配，完全一致算匹配，patter首位为*也算一致
        boolean match = (str.length() > 0 && (str.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));

        // 如pattern为 b*az
        if (pattern.length() > 1 && pattern.charAt(1) == '*'){
                   // str = "azzb", pattern = "a*azzb"
            return match(str, pattern.substring(2))
                    || (match && match(str.substring(1), pattern));
        }
        else {
            // 这里要注意,pattern长度为1时，subString(1)结果为空字符串
            return match && match(str.substring(1), pattern.substring(1));
        }
    }

    public static void main(String[] args) {
        RegexMatch regexMatch = new RegexMatch();
        boolean az = regexMatch.match("az", ".");
        System.out.println(az);
    }


}
