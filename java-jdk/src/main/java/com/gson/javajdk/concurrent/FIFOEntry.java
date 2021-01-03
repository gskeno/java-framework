package com.gson.javajdk.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class FIFOEntry<E extends Comparable<? super E>> implements Comparable<FIFOEntry<E>> {
    static final AtomicLong seq = new AtomicLong(0);
    final long seqNum;
    final E entry;

    public FIFOEntry(E entry) {
        seqNum = seq.getAndIncrement();
        this.entry = entry;
    }

    public E getEntry() {
        return entry;
    }

    @Override
    public int compareTo(FIFOEntry<E> other) {
        int res = entry.compareTo(other.entry);
        if (res == 0 && other.entry != this.entry) {
            res = (seqNum < other.seqNum ? -1 : 1);
        }
        return res;
    }

    @Override
    public String toString() {
        return "FIFOEntry{" +
                "seqNum=" + seqNum +
                ", entry=" + entry +
                '}';
    }
}
