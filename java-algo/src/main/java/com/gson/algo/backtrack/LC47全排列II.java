package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations-ii/
 *
 * 有重复元素的全排列
 * 输入：nums = [1,1,2]
 * 输出：
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 *
 * 将原始元素先做一下排序。比如排序后为 nums= [1,1,1,2], visit[] 表示nums中的每个元素是否被挑选过
 * 设backtrack(idx,path)函数表示当前选择路径为path,目前要在idx(起始值为0)位置挑选一个数放在上面
 *
 * 如果要挑选的元素存在重复值，只能挑选具有重复元素值的第一个元素，并标记其被挑选了，继续递归
 *
 * 比如 第一个元素选1，则只能选nums[0]，标记visit[0]=true。而不能选nums[1]和nums[2]
 *     第二个元素选2，只能选nums[3]=2
 *     第三个元素选1，则只能选nums[1],标记visit[1]=true。因为nums[0]已经被选过,nums[1]是具有重复值1的元素中的第一个，
 *                  不能选nums[2]。即重复元素值a1a2a3，a1没被选过，就不能选择a2。
 *     第四个元素选1，只能选nums[2]
 *
 *
 *
 */
public class LC47全排列II {

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visit = new boolean[nums.length];
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(result, 0, visit, nums, path);
        return result;
    }

    /**
     *
     * @param result
     * @param idx 要生成的排列的idx索引位置要填充值了
     * @param visit
     * @param nums
     * @param path
     */
    private void helper(List<List<Integer>> result, int idx,  boolean[] visit, int[] nums, List<Integer> path){

        if (idx == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 这个元素已经被选择过，不再选择
            if (visit[i]){
                continue;
            }
            // 当前元素是重复元素，且不是重复元素的第一个元素，且它的前一个同值元素没被选择，则该元素也不能选择
            // 如 [1,1,2,2,2,3], 第一个2是可以选的，但是如果第一个2没被选择，确要选第二个2，是不被允许的
            if (i > 0 && nums[i] == nums[i-1] && !visit[i-1]){
                continue;
            }

            // 选择
            path.add(nums[i]);
            visit[i] = true;
            // 递归
            helper(result, idx+1, visit, nums, path);
            // 恢复状态
            path.remove(path.size()-1);
            visit[i] =false;
        }
    }

    public static void main(String[] args) {
        LC47全排列II solution = new LC47全排列II();
        List<List<Integer>> lists;
        lists = solution.permuteUnique(new int[]{1, 1, 2});
        System.out.println(lists);

        lists = solution.permuteUnique(new int[]{1, 1, 1, 2});
        System.out.println(lists);

        lists = solution.permuteUnique(new int[]{1, 1, 2, 2, 3});
        System.out.println(lists);
    }
}
