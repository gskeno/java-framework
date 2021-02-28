package com.gson.algo.array;

/**
 * https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tqId=11181&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MoreThanHalfNum {

    //设置候选者为-1，候选票数为0
    //加入数组中存在众数，那么众数一定大于数组的长度的一半。
    //思想就是：如果两个数不相等，就消去这两个数，最坏情况下，每次消去一个众数和一个非众数，那么如果存在众数，最后留下的数肯定是众数。
    // 众数是最后留下的，但最后留下的不一定是众数
    //
    //具体做法：
    //
    //初始化：候选人cond = -1， 候选人的投票次数cnt = 0
    //遍历数组，如果cnt=0， 表示没有候选人，则选取当前数为候选人，++cnt
    //否则，如果cnt > 0, 表示有候选人，如果当前数=cond，则++cnt，否则--cnt
    //直到数组遍历完毕，最后检查cond是否为众数
    public int MoreThanHalfNum_Solution(int [] array) {
        //候选者
        int candidate = -1;
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (cnt == 0){
                candidate = array[i];
                cnt ++;
            }else {
                if (candidate == array[i]){
                    cnt ++;
                }else {
                    cnt --;
                }
            }
        }
        cnt = 0;
        for (int i = 0; i < array.length; i++) {
          if (candidate == array[i]) {
              cnt ++ ;
          }
        }

        if (cnt > array.length/2){
            return candidate;
        }
        return 0;
    }

    public static void main(String[] args) {
        MoreThanHalfNum moreThanHalfNum = new MoreThanHalfNum();
        int[] a = {2,0,2,0,2,0};
        System.out.println(moreThanHalfNum.MoreThanHalfNum_Solution(a));

        int[] b = {2,0,2,0,2,2,0};
        System.out.println(moreThanHalfNum.MoreThanHalfNum_Solution(b));
    }
}
