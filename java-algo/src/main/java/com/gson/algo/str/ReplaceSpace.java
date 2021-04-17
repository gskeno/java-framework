package com.gson.algo.str;

/**
 *
 * https://www.nowcoder.com/practice/0e26e5551f2b489b9f58bc83aa4b6c68?tpId=13&tqId=11155&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 */
public class ReplaceSpace {

    public String replaceSpace (String s) {
        char[] a = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<a.length;i++)
        {
            if(a[i]==' '){
                sb.append("%20");
            }else{
                sb.append(a[i]);
            }
        }
        return sb.toString();
        // write code here
    }
}
