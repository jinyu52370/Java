package com.jinyu.impl;

import com.jinyu.Stack;
import com.jinyu.util.Stacks;
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

    @Test
    public void calculatorTest1(){
        System.out.println(Stacks.calculator("500+1000-2*2+8/4+36/18-666/333+678/678"));
    }

    @Test
    public void test(){
        String str = "123456";
        System.out.println(str.substring(5, 6));
    }
}
