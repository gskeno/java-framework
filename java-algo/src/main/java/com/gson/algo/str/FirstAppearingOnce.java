package com.gson.algo.str;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 */
public class FirstAppearingOnce {

    int[] charCnt = new int[128];
    Queue<Character> queue = new LinkedList<Character>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        //至出线一次，该位置为1；
        //出现n次，该位置为n
        if (charCnt[ch]++ == 0) //新来的单身字符，入队
        {
            queue.add(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        Character CHAR = null;
        char c = 0;
        while ((CHAR = queue.peek()) != null) {
            c = CHAR.charValue();
            if (charCnt[c] == 1) { //判断是否脱单了，没脱单则输出
                return c;
            } else {
                queue.remove(); //脱单了就移出队列，它不会再回来了
            }
        }
        return '#'; //队空，返回#
    }
}
