package com.gson.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class BasicEvent<T> extends ApplicationEvent
        implements ResolvableTypeProvider
{

    private T t;

    public BasicEvent(T t){
        super(t);
        this.t = t;
    }

    public T getT() {
        return t;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getSource()));
    }
}
