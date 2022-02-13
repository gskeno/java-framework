package com.gson.algo.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 恢复ip地址
 * 比如1021012101
 * 可以是ip地址102.101.210.1
 * 也可以是10.210.12.101
 * 有以下特点，3个点号分隔成了四个段
 * 每个段的数组在0到255之间，段内数字可以是0，但不能是以0开头的多个数字
 */
public class RestoreIpAddress {

    public List<String> restoreIpAddress(String s){
        List<String> result = new LinkedList<>();
        helper(s, 0,0, "", "", result);
        return result;
    }

    /**
     *
     * @param s 要处理的原字符串，这个函数内部一直都会改变
     * @param i 当前要处理的字符在s中的位置，范围为 0到s.length-1
     * @param segI 当前要处理的字符所在的段，总共有4个段，范围为0到3
     * @param seg 当前要处理的字符所在的段的内容
     * @param ip  已处理好的段拼接起来的ip内容(可能内容还未完整，需要继续补充段)
     * @param result
     */
    private void helper(String s, int i, int segI, String seg, String ip, List<String> result){
        if (i == s.length()){
            result.add( ip + seg);
            return;
        }
        if ( i < s.length()){
            char currentChar = s.charAt(i);
            // 当前段后追加该数字，当前段依然生效，则不生成新的段，可以接着处理下一个数字
            if (isValidSeg(seg + currentChar)){
                helper(s, i +1, segI, seg + currentChar, ip, result);
            }

            // 当前段如果已经有数字(长度不为0），且当前还未处理到最后一个段，也可以将当前处理的数字当作下一个段的开始数字
            if (seg.length() > 0 && segI < 3){
                helper(s, i+1, segI + 1, "" + currentChar, ip + seg + ".", result);
            }
        }
    }

    /** 在0到255之间，且要么是0，要么不以0开头
     * Integer.valueOf("04") = 4
     */
    private boolean isValidSeg(String seg){
        return  Integer.valueOf(seg) <= 255
                && Integer.valueOf(seg) >= 0
                && (seg.equals("0") || seg.charAt(0) != '0');
    }
    public static void main(String[] args) {
        String s = "1021012101";
        RestoreIpAddress restoreIpAddress = new RestoreIpAddress();
        List<String> stringList = restoreIpAddress.restoreIpAddress(s);
        System.out.println(stringList);
    }
}
