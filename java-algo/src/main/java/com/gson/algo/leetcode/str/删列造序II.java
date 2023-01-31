package com.gson.algo.leetcode.str;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/delete-columns-to-make-sorted-ii/
 * 给定由 n 个字符串组成的数组 strs，其中每个字符串长度相等。
 *
 * 选取一个删除索引序列，对于 strs 中的每个字符串，删除对应每个索引处的字符。
 *
 * 比如，有 strs = ["abcdef", "uvwxyz"]，删除索引序列{0, 2, 3}，删除后 strs 为["bef", "vyz"]。
 *
 * 假设，我们选择了一组删除索引 answer，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（strs[0] <= strs[1] <= strs[2] ... <= strs[n - 1]）排列的，然后请你返回 answer.length的最小可能值。
 *
 
 *
 */
public class 删列造序II {

    /**
     * 提示1: 方向思考，尽量保留更多的列，保证有序。对于非删不可的列才考虑删除
     *        当前列绝对有序，则可以保留；
     *        当前列不绝对有序时，需要与前一列拼接起来，来判断当前列是否可以保留，比如ac,db。
     *        虽然第而列c>b，不有序；但是与前一列拼接起来,ac < db，就有序了
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] A) {
        int N = A.length;
        int W = A[0].length();
        int ans = 0;

        // cur : all rows we have written
        // For example, with A = ["abc","def","ghi"] we might have
        // cur = ["ab", "de", "gh"].
        String[] cur = new String[N];
        for (int j = 0; j < W; ++j) {
            // cur2 : What we potentially can write, including the
            //        newest column col = [A[i][j] for i]
            // Eg. if cur = ["ab","de","gh"] and col = ("c","f","i"),
            // then cur2 = ["abc","def","ghi"].
            String[] cur2 = Arrays.copyOf(cur, N);
            for (int i = 0; i < N; ++i)
                cur2[i] += A[i].charAt(j);

            if (isSorted(cur2))
                cur = cur2;
            else
                ans++;
        }

        return ans;
    }

    public boolean isSorted(String[] A) {
        for (int i = 0; i < A.length - 1; ++i)
            if (A[i].compareTo(A[i+1]) > 0)
                return false;

        return true;
    }

    public static void main(String[] args) {
        删列造序II solution = new 删列造序II();
        int ans = 0;
        ans = solution.minDeletionSize(new String[]{"xc","yb","za"});
        System.out.println(ans);

        ans = solution.minDeletionSize(new String[]{"ca","bb","ac"});
        System.out.println(ans);

    }
}
