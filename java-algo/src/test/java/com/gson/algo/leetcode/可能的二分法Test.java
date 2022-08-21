package com.gson.algo.leetcode;

import org.junit.Test;

public class 可能的二分法Test {
    private 可能的二分法 solution = new 可能的二分法();

    @Test
    public void test1(){
                System.out.println(solution.possibleBipartition(4, new int[][]{
                {1,2},
                {1,3},
                {2,4}
        }));
    }

    @Test
    public void test2(){
                System.out.println(solution.possibleBipartition(3, new int[][]{
                {1,2},
                {1,3},
                {2,3}
        }));
    }

    @Test
    public void test3(){
        System.out.println(solution.possibleBipartition(5, new int[][]{
                {1,2},
                {2,3},
                {3,4},
                {4,5},
                {1,5}
        }));
    }

    @Test
    public void test4(){
                System.out.println(solution.possibleBipartition(10, new int[][]{
                {1,2},
                {3,4},
                {5,6},
                {6,7},
                {8,9},
                {7,8}

        }));
    }
}
