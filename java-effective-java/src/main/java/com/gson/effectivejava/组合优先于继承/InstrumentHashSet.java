package com.gson.effectivejava.组合优先于继承;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用，增加add的计数
 */
public class InstrumentHashSet<E>  extends HashSet<E> {

    private int addCount;

    public InstrumentHashSet(){

    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    /**
     * 使用继承打破了封装，可能会导致子调用父亲方法，父亲方法又调用子方法。
     * 这里的addAll。父类执行时会再调用子类的add方法，导致增加元素次数被计数2次
     * @param c collection containing elements to be added to this collection
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public static void main(String[] args) {
        InstrumentHashSet<String> instrumentSet = new InstrumentHashSet<>();
        Set<String> sets = new HashSet<>();
        sets.add("1");
        sets.add("2");
        sets.add("3");

        instrumentSet.addAll(sets);
        System.out.println(instrumentSet.addCount);
    }
}
