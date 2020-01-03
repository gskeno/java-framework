package com.gson.spring.bean;

public class Forest {
    private Tree singleTree;

    public void setAnTree(Tree tree){
        this.singleTree = tree;
    }

    @Override
    public String toString() {
        return "Forest{" +
                "singleTree=" + singleTree +
                '}';
    }
}
