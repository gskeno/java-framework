package com.gson.guava;

import com.google.common.util.concurrent.AbstractFuture;

public class DefaultListenableFuture<T> extends AbstractFuture<T> {

    public DefaultListenableFuture(){

    }

    @Override
    public boolean set(T value) {
        return super.set(value);
    }
}
