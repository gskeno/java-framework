package com.gson.algo.huawei;

import java.util.Scanner;

public class HJ33整数与IP地址的转换 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String ip = scanner.nextLine();
            String num = scanner.nextLine();
            convert(ip, num);
        }
    }

    public static void convert(String ip, String numStr){
        String[] ips = ip.split("\\.");
        long result  = 0;
        for(String segment : ips){
            result = result * 256 + Integer.parseInt(segment);
        }
        System.out.println(result);

        Long num = Long.parseLong(numStr);
        String res = "";
        while (num != 0){
            res = num % 256 + "." + res;
            num = num /256;
        }
        System.out.println(res.substring(0, res.length() - 1));
    }
}
