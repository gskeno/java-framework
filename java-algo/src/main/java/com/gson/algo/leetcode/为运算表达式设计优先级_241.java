package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/different-ways-to-add-parentheses/
 * 给你一个由数字和运算符组成的字符串expression ，按不同优先级组合数字和运算符，
 * 计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * <p>
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * 方法1: 记忆化搜索 + 递归 (归并思想)
 * 方法2: 动态规划
 * <p>
 * dp[left][right]表示从expression[left]到expression[right]所组成的表达式能产生的全部结果，结果肯定是一个集合，
 * 所以dp的元素是集合。
 * <p>
 * 对于2*3-4*5，那么dp[0,2]的结果为{6}, dp[0,4]的结果为{2,-2}
 * <p>
 * 最终答案为dp[0][expression.length()-1]
 * <p>
 * 可以用归并法则，自上而下处理，比如求dp[0,6],以任意一个操作符作为分界线(有*，-，*三个操作符),
 * dp[0][6] = dp[0][0]组合[2,6] 并集 dp[0,2]组合dp[4,6] 并集 dp[0,4]组合dp[6,6]
 */
public class 为运算表达式设计优先级_241 {

    static final int ADDITION = -1;
    static final int SUBTRACTION = -2;
    static final int MULTIPLICATION = -3;

    public List<Integer> diffWaysToCompute1(String expression) {
        // 把字符串转化为操作数
        // eg ops={2,-3,3,-2,4,-3,5}表示 2 * 3 - 4 * 5
        List<Integer> ops = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isDigit(expression.charAt(i))) {
                ops.add(expression.charAt(i) == '+' ? ADDITION : expression.charAt(i) == '-' ? SUBTRACTION : MULTIPLICATION);
            } else {
                int t = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    t = t * 10 + expression.charAt(i) - '0';
                    i++;
                }
                i--;
                ops.add(t);
            }
        }
        // 注意，这里不是expression.length，而是ops.size，因为表达式用数字来转译了
        // List<Integer> [][] dp = new List<>[expression.length()][expression.length()];
        List<Integer>[][] dp = new List[ops.size()][ops.size()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j < dp[0].length; j++) {
                dp[i][j] = new ArrayList<>();
                // 单个数字的运算操作组合结果永远都是自己，这一种情况
                if (i == j && (i&1) == 0) {
                    dp[i][j].add(ops.get(i));
                }
            }
        }

        dfs(dp, 0, ops.size() - 1, ops);
        return dp[0][ops.size() - 1];
    }

    private List<Integer> dfs(List<Integer>[][] dp, int left, int right, List<Integer> ops) {
        // 记忆化搜索，已经存储过计算记过，直接返回,不再计算，否则进行首次计算，存储记忆以备下次查询
        System.out.println(left + "," + right);
        if (dp[left][right].isEmpty()){
            // 注意，这里是k+=2,因为连续的两个运算符之间是隔了一个数字的
            // left,k,right指向的都是操作数，且都是偶数
            for (int k = left ; k < right; k+=2) {
                List<Integer> leftRes = dfs(dp, left, k, ops);
                List<Integer> rightRes = dfs(dp, k+2, right, ops);
                Integer operator = ops.get(k + 1);
                for(int leftV : leftRes){
                    for(int rightV : rightRes){
                        if (operator == ADDITION){
                            dp[left][right].add(leftV + rightV);
                        }else if (operator == SUBTRACTION){
                            dp[left][right].add(leftV - rightV);
                        }else {
                            dp[left][right].add(leftV * rightV);
                        }
                    }
                }
            }
        }
        return dp[left][right];
    }


    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ops = new ArrayList<Integer>();
        for (int i = 0; i < expression.length(); ) {
            if (!Character.isDigit(expression.charAt(i))) {
                if (expression.charAt(i) == '+') {
                    ops.add(ADDITION);
                } else if (expression.charAt(i) == '-') {
                    ops.add(SUBTRACTION);
                } else {
                    ops.add(MULTIPLICATION);
                }
                i++;
            } else {
                int t = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    t = t * 10 + expression.charAt(i) - '0';
                    i++;
                }
                ops.add(t);
            }
        }
        List<Integer>[][] dp = new List[ops.size()][ops.size()];
        for (int i = 0; i < ops.size(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                dp[i][j] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < ops.size(); i += 2) {
            dp[i][i].add(ops.get(i));
        }
        for (int i = 3; i <= ops.size(); i += 2) {
            for (int j = 0; j + i <= ops.size(); j += 2) {
                int l = j;
                int r = j + i - 1;
                for (int k = j + 1; k < r; k += 2) {
                    List<Integer> left = dp[l][k - 1];
                    List<Integer> right = dp[k + 1][r];
                    for (int num1 : left) {
                        for (int num2 : right) {
                            if (ops.get(k) == ADDITION) {
                                dp[l][r].add(num1 + num2);
                            } else if (ops.get(k) == SUBTRACTION) {
                                dp[l][r].add(num1 - num2);
                            } else if (ops.get(k) == MULTIPLICATION) {
                                dp[l][r].add(num1 * num2);
                            }
                        }
                    }
                }
            }
        }
        return dp[0][ops.size() - 1];
    }

    public static void main(String[] args) {
        为运算表达式设计优先级_241 solution = new 为运算表达式设计优先级_241();
        List<Integer> list = solution.diffWaysToCompute("2*3-4*5");
        System.out.println(list);
        list = solution.diffWaysToCompute1("2*3-4*5");
        System.out.println(list);

    }
}
