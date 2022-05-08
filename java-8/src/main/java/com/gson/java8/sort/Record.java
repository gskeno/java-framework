package com.gson.java8.sort;

public class Record {
    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "time=" + time +
                '}';
    }

    /**
     * public static String getMaxLenSubDigitStr(String line){
     *         int maxLen = 0;
     *         boolean legalStrBegin = false;
     *         boolean legalStrEnd = false;
     *         int begin = -1;
     *         int end = -1;
     *         String maxSubStr = "";
     *         char[] elements = line.toCharArray();
     *         // 能开头的只能是数字
     *         // 1234567890abcd9.+12345.678.9ed
     *         // +12345.678
     *         // 是否已经出现点号
     *         boolean firstDotOccur =false;
     *         boolean secondDotOccur = false;
     *         boolean containDot = false;
     *         for (int i = 0; i < elements.length; i++) {
     *             // 已经有一个可能的合法字符串开始了
     *             if (elements[i] == '+' || elements[i] == '-' || Character.isDigit(elements[i])){
     *                if (!legalStrBegin){
     *                    legalStrBegin = true;
     *                    begin = i;
     *                    continue;
     *                }
     *             }
     * //            // 第一次出现点号
     * //            firstDotOccur = !firstDotOccur && elements[i] == '.';
     * //            // 当前是点号且之前出现过一个点号，则这里是第二个点号，可能的合法字符串不能再延长了
     * //            secondDotOccur = elements[i] == '.' && firstDotOccur;
     *             // 当前字符不是数字也不是点号，则可能的合法字符串不能再延长了
     *             boolean notDigitAndDot = !Character.isDigit(elements[i]) && elements[i] != '.';
     *
     *             // 合法字符串已经开始了，但是当前字符不再合法，截断了
     *             if ((elements[i] == '.'  || notDigitAndDot) && legalStrBegin){
     *                 end = i;
     *                 legalStrEnd = true;
     *                 if (secondDotOccur){
     *                     firstDotOccur=false;
     *                     secondDotOccur = false;
     *                 }
     *             }
     *
     *             if (legalStrBegin && legalStrEnd){
     *                 String sub = line.substring(begin, end);
     *                 if (sub.length()>1 && sub.charAt(sub.length()-1) == '*'){
     *                     sub = sub.substring(0, sub.length()-1);
     *                 }
     *                 if (sub.length()>=maxLen){
     *                     maxLen = sub.length();
     *                     maxSubStr = sub;
     *                 }
     *                 legalStrBegin = false;
     *                 legalStrEnd = false;
     *             }
     *         }
     *         return maxSubStr;
     *     }
     */
}
