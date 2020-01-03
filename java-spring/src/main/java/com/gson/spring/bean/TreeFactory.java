package com.gson.spring.bean;

import org.springframework.beans.factory.FactoryBean;

public class TreeFactory implements FactoryBean<Tree> {
    @Override
    public Tree getObject() throws Exception {
        Tree tree = new Tree();
        tree.setDiameter("2.43");
        tree.setHeight("9");
        return tree;
    }

    @Override
    public Class<?> getObjectType() {
        return Tree.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
