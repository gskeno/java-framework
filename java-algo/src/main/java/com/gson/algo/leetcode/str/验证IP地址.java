package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/validate-ip-address/
 */
public class 验证IP地址 {

    public String validIPAddress(String queryIP) {
        if (isIPv4(queryIP)){
            return "IPv4";
        }
        if (isIPv6(queryIP)){
            return "IPv6";
        }
        return  "Neither";
    }

    private boolean isIPv4(String ip){
        if(ip.endsWith(".")){
            return false;
        }
        String[] segments = ip.split("\\.");
        if (segments.length != 4){
            return false;
        }
        for(String segment : segments){
            if (segment.length() < 1 || segment.length() > 3){
                return false;
            }
            // 前到0的个数
            int leadingZeros = 0;
            int i = 0;
            while (i < segment.length() && segment.charAt(i++) == '0'){
                leadingZeros++;
            }
            if (leadingZeros > 0 && segment.length() > 1){
                return false;
            }
            for(char c : segment.toCharArray()){
                if (!Character.isDigit(c)){
                    return false;
                }
            }
            if (Integer.valueOf(segment) > 255){
                return false;
            }
        }

        return true;
    }

    private boolean isIPv6(String ip){
        if(ip.endsWith(":")){
            return false;
        }
        String[] segments = ip.split(":");
        if (segments.length != 8){
            return false;
        }
        for(String segment : segments){
            if (segment.length() < 1 || segment.length() > 4){
                return false;
            }
            for(char c : segment.toCharArray()){
                if (Character.isDigit(c)){
                    continue;
                }
                if (c >= 'a' && c <= 'f'){
                    continue;
                }

                if (c >= 'A' && c <= 'F'){
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        验证IP地址 solution = new 验证IP地址();
        String ans;
        ans = solution.validIPAddress("00.0.0.0");
        System.out.println(ans);

        ans = solution.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
        System.out.println(ans);

        ans = solution.validIPAddress("172.16.254.1");
        System.out.println(ans);

        ans = solution.validIPAddress("256.256.256.256");
        System.out.println(ans);





    }
}
