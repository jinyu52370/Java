package com.jinyu.impl;

import com.jinyu.Stack;
import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 12:30
 */
public class StackTest {
    @Test
    public void arrayStackTest1(){
        Stack stack = new StackUseArray(10);
        for (int i = 0; i < 10; i++) {
            stack.push(new Object[]{i});
        }
        stack.print();

        stack.pop();
        stack.print();
    }

    @Test
    public void stackUseLinkedListTest1(){
        Stack stack = new StackUseLinkedList();
        for (int i = 0; i < 10; i++) {
            stack.push(new Object[]{i});
        }
        stack.print();

        stack.pop();
        stack.print();
    }
}
