package com.gson.algo.huawei;

import java.util.Scanner;

public class HJ107求解立方根 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            double input = scanner.nextDouble();
            if (input == 0){
                System.out.println(0);
            }else if (input > 0){
                System.out.println(parseDouble(getCubeRoot(input)));
            }else {
                System.out.println(-parseDouble(getCubeRoot(-input)));
            }
        }
    }

    public static double parseDouble(double d){
        String dAftFormat = String.format("%.1f", d);
        return Double.parseDouble(dAftFormat);
    }

    /**
     * 获取立方根
     * 有bug，待修复
     * @return
     */
    public static  double getCubeRoot(double input){
        double left = 0;
        double right = input;
        if (input < 1){
            right = 1;
        }
        // 取1位小数
        // double mid = Double.parseDouble(String.format("%.1f", (left + right)/2));
        double precision = 0.01d;
        while (true){
            double mid = (left + right) /2;
            double cube = mid * mid * mid;
            if (cube < input && Math.abs(input - cube) > precision ){
                left = mid;
            }else if (cube > input && Math.abs(cube - input) > precision){
                right = mid;
            }else {
                return mid;
            }
        }
    }
}
