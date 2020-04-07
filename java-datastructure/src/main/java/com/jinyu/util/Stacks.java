package com.jinyu.util;

import com.jinyu.Stack;
import com.jinyu.impl.StackUseLinkedList;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 17:48
 */
public class Stacks {
    public static boolean isOperator(char operator){
        return ('+' == operator || '-' == operator || '*' == operator || '/' == operator);
    }

    public static int priority(char operator){
        if ('+' == operator || '-' == operator){
            return 0;
        }
        if ('*' == operator || '/' == operator){
            return 1;
        }
        return -1;
    }

    public static int operate(int num1, int num2, char operator){
        switch (operator){
            case '+':return num2 + num1;
            case '-':return num2 - num1;
            case '*':return num2 * num1;
            case '/':return num2 / num1;
            default:throw new RuntimeException("运算符错误！");
        }
    }

    /**
     * 简单中缀计算器(+ - * /)
     *
     * 1+1-2*3+8/2
     */
    public static int calculator(String expression){
        Stack numberStack = new StackUseLinkedList();
        Stack operatorStack = new StackUseLinkedList();

        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isOperator(chars[i])){
                if (operatorStack.isEmpty()){
                    operatorStack.push(chars[i]);
                    continue;
                }
                if (priority(chars[i]) > priority((char)operatorStack.peek())){
                    operatorStack.push(chars[i]);
                    continue;
                }
                while (!operatorStack.isEmpty()) {
                    int result = operate((int) numberStack.pop(), (int) numberStack.pop(), (char) operatorStack.pop());
                    numberStack.push(result);
                }
                operatorStack.push(chars[i]);
            } else {
                int j = i;
                try {
                    while (!isOperator((chars[j]))){
                        j++;
                    }
                    numberStack.push(Integer.valueOf(expression.substring(i, j)));
                    i = j - 1;
                } catch (Exception e) {
                    numberStack.push(Integer.valueOf(expression.substring(i, j)));
                    break;
                }
            }
        }
        while (!operatorStack.isEmpty()){
            int result = operate((int)numberStack.pop(), (int)numberStack.pop(), (char) operatorStack.pop());
            numberStack.push(result);
        }
        return (int)numberStack.pop();
    }
}
