package com.gson.algo.tree;

import org.junit.Test;

public class 树的深度Test extends TreeBasicTest{

    @Test
    public void test(){
        树的深度 solution = new 树的深度();
        int depth = solution.getDepth(root1);
        System.out.println(depth);
    }
}
