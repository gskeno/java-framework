package com.gsonkeno.javagenerics;

public interface EventHandler<T extends Event> {

    void handle(T event);
}
