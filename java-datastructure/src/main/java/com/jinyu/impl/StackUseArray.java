package com.jinyu.impl;

import com.jinyu.Stack;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 12:19
 */
public class StackUseArray<T> implements Stack<T> {
    private int maxSize;
    private Object[] stack;
    private int top = -1;

    public StackUseArray(int maxSize){
        this.maxSize = maxSize;
        stack = new Object[maxSize];
    }

    @Override
    public boolean isFull(){
        return top == maxSize;
    }

    @Override
    public boolean isEmpty(){
        return top == -1;
    }

    @Override
    public void push(T t){
        if (isFull()){
            return;
        }
        top++;
        stack[top] = t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop(){
        if (isEmpty()){
            return null;
        }
        T temp = (T)stack[top];
        top--;
        return temp;
    }

    @Override
    public void print(){
        System.out.print("[");
        for (int i = top; i > -1; i--) {
            System.out.println(stack[i]);
            if (i != 0){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        return (T)stack[top];
    }
}
