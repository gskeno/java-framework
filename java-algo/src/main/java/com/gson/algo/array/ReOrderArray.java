package com.gson.algo.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * https://www.nowcoder.com/practice/ef1f53ef31ca408cada5093c8780f44b?tpId=13&tqId=11166&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 */
public class ReOrderArray {

    //
    //[1,2,3,4] --> [1,3,2,4]
    //[1,2,3,5,7,8]--> [1,3,5,7,2, 8]
    public int[] reOrderArray (int[] array) {
        if(array.length == 0){
            return array;
        }
        Queue<Integer> jishu = new LinkedList<>();
        Queue<Integer> oushu = new LinkedList<>();
        for(int i = 0;i < array.length ;i++){
            if(array[i] % 2 != 0){
                jishu.add(array[i]);
            }else{
                oushu.add(array[i]);
            }
        }
        for(int i = 0;i < array.length ;i++){
            if(!jishu.isEmpty()){
                array[i] = jishu.poll();
            }else{
                array[i] = oushu.poll();
            }
        }
        return array;
    }
}
