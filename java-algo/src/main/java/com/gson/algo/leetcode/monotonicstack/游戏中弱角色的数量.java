package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/the-number-of-weak-characters-in-the-game/
 */
public class 游戏中弱角色的数量 {
    /**
     * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
     * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
     * 返回 弱角色 的数量。
     *
     * 提示1: 按照攻击力 由小变大 排序，攻击力相等的角色分为一组，则前一组内角色的攻击力肯定小于后一组角色的攻击力
     * 提示2: 遍历排完序后的角色，如果后面存在 某组角色防御力 > 当前角色防御力，则当前角色肯定是一个弱角色。
     *
     * 考虑攻击力可能存在相等情况，当两个角色攻击力相等时，两个角色如何排序？
     * 答案是 使防御力强的放在前面，这样我们可以认为每组只有一个角色。如果attack[i] = attack[j], 且
     * defence[i] > defence[j]，角色i后与其攻击力相同的角色j，防御力肯定不及自身，角色i不会弱于角色j
     * 反言之，如果j>i, defence[j] > defence[i]，角色j的攻击力肯定大于角色i,
     * @param properties
     * @return
     */
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        // 倒序遍历properties, maxDefence记录遍历过的角色防御最大值
        int maxDefence = 0;
        int ans = 0;
        for (int i = properties.length - 1; i >=0 ; i--) {
            if (maxDefence > properties[i][1]){
                ans++;
            }
            maxDefence = Math.max(maxDefence, properties[i][1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        游戏中弱角色的数量 solution = new 游戏中弱角色的数量();
        int ans = 0;
        ans = solution.numberOfWeakCharacters(new int[][]{{5,5},{6,3},{3,6}});
        System.out.println(ans);

        ans = solution.numberOfWeakCharacters(new int[][]{{2,2}, {3,3}});
        System.out.println(ans);

        ans = solution.numberOfWeakCharacters(new int[][]{{1,5}, {10,4}, {4,3}});
        System.out.println(ans);
    }

}
