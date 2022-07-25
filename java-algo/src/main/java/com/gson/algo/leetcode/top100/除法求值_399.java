package com.gson.algo.leetcode.top100;

import java.util.*;

/**
 *
 */
public class 除法求值_399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         *
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }

    public static void main(String[] args) {
        除法求值_399 solution = new 除法求值_399();
        List<List<String>> equations = new ArrayList<>();
        List<String> equa1 = new ArrayList<>();
        equa1.add("a");
        equa1.add("b");
        equations.add(equa1);

        List<String> equa2 = new ArrayList<>();
        equa2.add("b");
        equa2.add("c");
        equations.add(equa2);

        List<String> equa3 = new ArrayList<>();
        equa3.add("bc");
        equa3.add("cd");
        equations.add(equa3);



        double[] values = new double[]{1.5,2.5,5.0};

        List<List<String>> queries = new ArrayList<>();
        List<String> query1 = new ArrayList<>();
        query1.add("a");
        query1.add("c");
        queries.add(query1);

        List<String> query2 = new ArrayList<>();
        query2.add("c");
        query2.add("b");
        queries.add(query2);

        List<String> query3 = new ArrayList<>();
        query3.add("bc");
        query3.add("cd");
        queries.add(query3);

        List<String> query4 = new ArrayList<>();
        query4.add("cd");
        query4.add("bc");
        queries.add(query4);

        double[] ans = solution.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(ans));
    }
}
