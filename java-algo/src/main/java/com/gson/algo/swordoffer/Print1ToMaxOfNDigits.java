package com.gson.algo.swordoffer;

public class Print1ToMaxOfNDigits {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int整型 最大位数
     * @return int整型一维数组
     */
    public char[] printNumbers (int n) {
        // write code here
        // 初始化
        char[] number = new char[n];
        for (int i = 0; i < n; i++) {
            number[i] ='0';
        }

        while (increment(number, number.length - 1)) {
            printCharArr(number);
        }

        return number;
    }


    public boolean increment(char[] number, int addIndex) {
        int value = (number[addIndex] - '0') + 1;
        if (value < 10) {
            number[addIndex] = (char) (value+'0');
            return true;
        } else {

            if (addIndex - 1 < 0) {
                return false;
            }

            number[addIndex] = '0';

            return increment(number, addIndex - 1);
        }
    }

    public void printCharArr(char[] number) {

        boolean flag = false;
        for (int i = 0; i < number.length; i++) {

            if (!flag && number[i] == '0') {
                continue;
            }

            if (!flag && number[i] > '0') {
                flag = true;
            }

            System.out.print(number[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Print1ToMaxOfNDigits print1ToMaxOfNDigits = new Print1ToMaxOfNDigits();
        print1ToMaxOfNDigits.printNumbers(20);
    }
}
