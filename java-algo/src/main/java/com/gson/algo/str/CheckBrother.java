package com.gson.algo.str;

public class CheckBrother {

    public boolean checkBrother(String str1, String str2){
        int[] arr = new int[26];
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        for(int i=0; i<ch1.length; i++){
            arr[ch1[i]-'a']++;
            arr[ch2[i]-'a']--;
        }
        for(int i=0; i<26; i++){
            if(arr[i]!=0){
                return false;
            }
        }
        return true;
    }
}
