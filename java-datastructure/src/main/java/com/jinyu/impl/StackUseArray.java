package com.jinyu.impl;

import com.jinyu.Stack;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 12:19
 */
public class StackUseArray implements Stack {
    private int maxSize;
    private Object[][] stack;
    private int top = -1;

    public StackUseArray(int maxSize){
        this.maxSize = maxSize;
        stack = new Object[maxSize][];
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
    public void push(Object[] objects){
        if (isFull()){
            return;
        }
        top++;
        stack[top] = objects;
    }

    @Override
    public Object[] pop(){
        if (isEmpty()){
            return null;
        }
        Object[] temp = stack[top];
        top--;
        return temp;
    }

    @Override
    public void print(){
        System.out.print("[");
        for (int i = top; i > -1; i--) {
            System.out.print("[");
            for (int j = 0; j < stack[i].length; j++) {
                System.out.print(stack[i][j]);
                if (j != stack[i].length){
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i != 0){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
