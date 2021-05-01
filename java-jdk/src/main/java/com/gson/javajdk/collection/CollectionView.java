package com.gson.javajdk.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 集合视图
 */
public class CollectionView {

    /**
     * 白名单
     */
    private Set<String> whiteSet;

    /**
     * eg:初始化时从配置中心拉取最新的配置赋给whiteSet;
     * whiteSet是只读的，不可修改
     *
     * 使用Collections.unmodifiableSet获取只读视图
     * 防止用户在使用过程中修改容器
     */
    private void init(){
        //模拟拉取远程配置到本地
        Set<String> temp = new HashSet<>();
        temp.add("91793y8");

        whiteSet = Collections.unmodifiableSet(temp);
    }

    public Set<String> getWhiteSet() {
        return whiteSet;
    }

    public static void main(String[] args) {
        CollectionView view = new CollectionView();
        view.init();

        Set<String> whiteSet = view.getWhiteSet();
        //java.lang.UnsupportedOperationException
        whiteSet.add("19793");
    }

}
