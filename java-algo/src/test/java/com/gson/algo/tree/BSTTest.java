package com.gson.algo.tree;

import org.junit.Before;
import org.junit.Test;

/**
 *             4(A)
 *        2(B)        6(C)
 *    1(D)   3(E)  5(F)  7(G)
 */
public class BSTTest {
    private BST<Integer, String> tree;

    @Before
    public void init(){
        tree = new BST<>();
        tree.put(4,"A");
        tree.put(2,"B");
        tree.put(6,"C");
        tree.put(1,"D");
        tree.put(3,"E");
        tree.put(5,"F");
        tree.put(7,"G");
    }

    @Test
    public void testPreTravel(){
        tree.preOrderTravel();
    }
}
