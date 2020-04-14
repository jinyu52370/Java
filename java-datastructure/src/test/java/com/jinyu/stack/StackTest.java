package com.jinyu.stack;

import com.jinyu.stack.Stack;
import com.jinyu.stack.StackUseArray;
import com.jinyu.stack.StackUseLinkedList;
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
//        System.out.println(Stacks.calculator("7-6-1-8"));
    }

    @Test
    public void polandNotationTest1(){
        System.out.println(Stacks.polandNotation("1+((2+3)*4)-5"));
    }

    @Test
    public void polandNotationTest2(){
        System.out.println(Stacks.infix2Suffix("1+((2+3)*4)-5"));
    }
}
