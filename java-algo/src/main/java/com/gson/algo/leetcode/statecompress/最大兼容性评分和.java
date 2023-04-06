package com.gson.algo.leetcode.statecompress;

/**
 * https://leetcode.cn/problems/maximum-compatibility-score-sum/
 */
public class 最大兼容性评分和 {
    private int m;
    private int n;
    private int[][] scores;
    private int maxScore;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        maxScore = 0;
        m = students.length;
        n = students[0].length;
        // m * m 矩阵表示每个学生与每个导师的兼容性评分
        scores = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int[] studentScore  = students[i];
                int[] mentorScore = mentors[j];
                for (int k = 0; k < n; k++) {
                    scores[i][j] += studentScore[k] == mentorScore[k] ? 1: 0;
                }
            }
        }
        // 导师被选择的状态
        boolean[] selected = new boolean[m];
        dfs(0, selected, 0);
        return maxScore;
    }

    /**
     * 第k个学生选择导师
     * @param k
     * @param selected
     * @param curScore 当前选择方案下的得分
     */
    private void dfs( int k, boolean[] selected, int curScore){
        if (k == m){
            maxScore = Math.max(maxScore, curScore);
            return;
        }
        // 哪个导师没有被选，都可以去选择
        for (int i = 0; i < m; i++) {
            if (selected[i] == false){
                // 标记被选择
                selected[i] = true;
                dfs(k+1, selected, curScore +scores[k][i]);
                // 回溯
                selected[i] = false;
            }
        }
    }

    /**
     * mask表示老师被选择的状态，二进制表示时第i位为1，表示第i个导师已经被选择
     * f(mask)表示导师选择状态为mask时最大的兼容性评分。
     *
     * 我们强制约定按照学生编号依次给学生分配导师，那么mask中有c个1时，表示学生
     * 编号0，1，2，...., c-1 已经分配了导师。
     *
     * 我们可以枚举编号为c-1的学生被分配到哪一位导师，即mask中二进制第i为1的导师编号i都可以被分配
     * @param students
     * @param mentors
     * @return
     */
    public int maxCompatibilitySum1(int[][] students, int[][] mentors) {
        return 0;
    }

    public static void main(String[] args) {
        最大兼容性评分和 solution = new 最大兼容性评分和();
        int maxScore = 0;
        maxScore = solution.maxCompatibilitySum(new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 1}},
                new int[][]{{1, 0, 0}, {0, 0, 1}, {1, 1, 0}});
        System.out.println(maxScore);

        maxScore = solution.maxCompatibilitySum(new int[][]{{0, 0}, {0, 0}, {0, 0}},
                new int[][]{{1,1}, {1,1}, {1, 1}});
        System.out.println(maxScore);

        maxScore = solution.maxCompatibilitySum(new int[][]{{0,1,0,1,1,1},{1,0,0,1,0,1},{1,0,1,1,0,0}},
                                                new int[][]{{1,0,0,0,0,1},{0,1,0,0,1,1},{0,1,0,0,1,1}});
        System.out.println(maxScore);



    }
}
