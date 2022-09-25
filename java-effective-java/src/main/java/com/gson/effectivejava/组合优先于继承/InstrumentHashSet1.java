package com.gson.effectivejava.组合优先于继承;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用继承，增加add的计数
 */
public class InstrumentHashSet1<E>  implements Set<E> {
    private Set<E> set;
    public InstrumentHashSet1(Set<E> set){
       this.set = set;
    }
    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }



    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }



    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean add(E e) {
        addCount++;
        return set.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return set.addAll(c);
    }

    private int addCount;


    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        InstrumentHashSet1<String> instrumentSet = new InstrumentHashSet1<>(hashSet);
        Set<String> sets = new HashSet<>();
        sets.add("1");
        sets.add("2");
        sets.add("3");

        instrumentSet.addAll(sets);
        System.out.println(instrumentSet.addCount);
    }
}
