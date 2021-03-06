package com.jinyu.queue;

public interface Queue {
    boolean isFull();

    boolean isEmpty();

    void add(Object o);

    Object get();

    int length();

    void print();

    Object peek();
}
