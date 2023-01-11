package com.gson.algo.leetcode.array;

/**
 * https://leetcode.cn/problems/majority-element/
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 
 *
 * 示例1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 * 示例2：
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 多数元素 {

    /**
     * 提示1: 最多只有一个元素满足条件，题目又限制元素必然存在，所以只有一个元素是答案。
     *
     * 提示2: 摩尔投票算法，两个不相同元素抵消，最后的元素可能是答案，需要校验。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int candidate  = 0;
        int vote = 0;
        for(int num : nums){
            if (vote > 0 && num == candidate){
                vote++;
            }else if (vote == 0){
                vote++;
                candidate = num;
            }else {
                vote--;
            }
        }
        int cnt = 0;
        for(int num : nums){
            if (num == candidate){
                cnt++;
            }
        }
        if (cnt > nums.length /2){
            return candidate;
        }
        // 走不到这里来
        return candidate;
    }
}
