package com.gson.algo.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/string-to-integer-atoi/
 */
public class 字符串转换整数atoi {

    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        for(char c : s.toCharArray()){
            automaton.get(c);
        }
        return (int)(automaton.sign * automaton.ans);
    }

    class Automaton{
        private String state = "start";
        public int sign = 1;
        public long ans = 0;

        private Map<String, String[]> states = new HashMap(){{
            put("start", new String[]{"start", "sign", "number", "end"});
            put("sign", new String[]{"end", "end", "number", "end"});
            put("number", new String[]{"end", "end", "number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        void get(char c){
            state = states.get(state)[getStateColumn(c)];
            if (state.equals("sign")){
                sign = c == '+'? 1 : -1;
            }
            if (state.equals("number")){
                ans = ans * 10 + c - '0';
                if (sign == 1 && ans > Integer.MAX_VALUE){
                    ans = Integer.MAX_VALUE;
                }
                if (sign == -1 && ans > - (long)Integer.MIN_VALUE){
                    ans = -(long) Integer.MIN_VALUE;
                }
            }
        }

        /**
         *
         * @param c 输入信息的编号
         * @return
         */
        int getStateColumn(char c){
            if (c == ' '){
                return 0;
            }
            if (c == '+' || c == '-'){
                return 1;
            }
            if (Character.isDigit(c)){
                return 2;
            }
            return 3;
        }
    }
}
