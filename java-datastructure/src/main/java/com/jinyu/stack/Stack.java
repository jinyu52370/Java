package com.jinyu.stack;

public interface Stack<T> {
    boolean isFull();

    boolean isEmpty();

    void push(T t);

    T pop();

    void print();

    T peek();
}
