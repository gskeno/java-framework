package com.gson.algo.huawei;


import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/2aa32b378a024755a3f251e75cbf233a
 */
public class HJ29字符串加解密 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            mixAndUnmix(line1, line2);
        }
    }

    /**
     *
     * @param plainText 要进行加密
     * @param mixText   要进行解密
     */
    public static void mixAndUnmix(String plainText, String mixText){
        char[] chars = plainText.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])){
                if (chars[i] == 'Z'){
                    chars[i] = 'A';
                }else if (chars[i] == 'z'){
                    chars[i] = 'a';
                }else {
                    chars[i] = (char)(chars[i] + 1);
                }
                if (Character.isLowerCase(chars[i])){
                    chars[i] = Character.toUpperCase(chars[i]);
                }else if (Character.isUpperCase(chars[i])){
                    chars[i] = Character.toLowerCase(chars[i]);
                }
            }else if (Character.isDigit(chars[i])){
                if (chars[i] == '9'){
                    chars[i] = '0';
                }else {
                    chars[i] = (char) (chars[i] + 1);
                }
            }
        }
        System.out.println(new java.lang.String(chars));

        char[] mixChars = mixText.toCharArray();
        for (int i = 0; i < mixChars.length; i++) {
            if (Character.isLetter(mixChars[i])){
                if (mixChars[i] == 'A'){
                    mixChars[i] = 'Z';
                }else if (mixChars[i] == 'a'){
                    mixChars[i] = 'z';
                }else {
                    mixChars[i] = (char)(mixChars[i] - 1);
                }
                if (Character.isLowerCase(mixChars[i])){
                    mixChars[i] = Character.toUpperCase(mixChars[i]);
                }else if (Character.isUpperCase(mixChars[i])){
                    mixChars[i] = Character.toLowerCase(mixChars[i]);
                }
            }else if (Character.isDigit(mixChars[i])){
                if (mixChars[i] == '0'){
                    mixChars[i] = '9';
                }else {
                    mixChars[i] = (char) (mixChars[i] - 1);
                }
            }
        }
        System.out.println(new String(mixChars));
    }
}