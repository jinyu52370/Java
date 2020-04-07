package com.jinyu;

public interface Stack {
    boolean isFull();

    boolean isEmpty();

    void push(Object[] objects);

    Object[] pop();

    void print();
}
