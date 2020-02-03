package com.gson.generic.base20200118;

public class KeyValueInfo<K,V,I> extends KeyValue<K, V> {
    private I info;

    public KeyValueInfo(K key, V value) {
        super(key, value);
    }

    public KeyValueInfo(K key, V value, I info) {
        super(key, value);
        this.info = info;
    }
}
