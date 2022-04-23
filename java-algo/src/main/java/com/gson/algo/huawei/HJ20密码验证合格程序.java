package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/184edec193864f0985ad2684fbc86841
 *
 * 密码要求:
 *
 * 1.长度超过8位
 *
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 *
 * 3.不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
 *
 * 数据范围：输入的字符串长度满足 1≤n≤100
 * 输入描述：
 * 一组字符串。
 *
 * 输出描述：
 * 如果符合要求输出：OK，否则输出NG
 */
public class HJ20密码验证合格程序 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String result = verify(line);
            System.out.println(result);
        }
    }

    public static String verify(String line){
        if (line.length() <=8){
            return "NG";
        }

        // 大写字母,小写字母,数字,其他符号, 满足条件，则该位置1
        int[] clauses = new int[4];
        for(char c : line.toCharArray()){
            if (c - 'A' >=0 && 'Z' - c >= 0 ){
                clauses[0] = 1;
            }else if (c - 'a' >= 0 && 'z' -c >=0){
                clauses[1] = 1;
            }else if (c - '0' >=0 && '9' - c >=0){
                clauses[2] = 1;
            }else if (clauses[3] == 0){
                clauses[3] = 1;
            }
        }
        int clauseMatchedNum = 0;
        for(int clause : clauses){
            clauseMatchedNum+= clause;
        }
        if (clauseMatchedNum <3){
            return "NG";
        }

        // 如果长度小于6，则不可能有 长度大于2的不含公共元素的子串重复，
        // 因为两个长度为3的不含公共元素的子串重复，至少需要6个字符
        if (line.length() < 6){
            return "OK";
        }

        for (int i = 0; i < line.length()-6; i++) {
            String subStr = line.substring(i, i + 3);
            if (line.substring(i+3).indexOf(subStr) != -1){
                return "NG";
            }
        }
        return  "OK";
    }
}
