package com.jinyu.util;

import com.jinyu.impl.StackUseLinkedList;

import java.util.*;
import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 17:48
 */
public class Stacks {
    /**
     * 判断char是否是运算符
     * @param operator 运算符
     * @return boolean
     */
    public static boolean isOperator(char operator){
        return ('+' == operator || '-' == operator || '*' == operator || '/' == operator);
    }

    /**
     * 获得运算符优先级
     * @param operator 运算符
     * @return 优先级
     */
    public static int priority(char operator){
        if ('+' == operator || '-' == operator){
            return 0;
        }
        if ('*' == operator || '/' == operator){
            return 1;
        }
        return -1;
    }

    /**
     * 运算
     * @param num1 数据1
     * @param num2 数据2
     * @param operator 运算符
     * @return 运算结果
     */
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
     */
    public static int calculator(String expression){
        StackUseLinkedList numberStack = new StackUseLinkedList();
        StackUseLinkedList operatorStack = new StackUseLinkedList();

        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isOperator(chars[i])) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(chars[i]);
                    continue;
                }
                if (priority(chars[i]) > priority((char) operatorStack.peek())) {
                    operatorStack.push(chars[i]);
                    continue;
                }
                while (!operatorStack.isEmpty()) {
                    int result = operate((int) numberStack.pop(), (int) numberStack.pop(), (char) operatorStack.pop());
                    numberStack.push(result);
                }
                operatorStack.push(chars[i]);
                continue;
            }
            int j = i;
            try {
                while (!isOperator((chars[j]))) {
                    j++;
                }
                numberStack.push(Integer.valueOf(expression.substring(i, j)));
                i = j - 1;
            } catch (Exception e) {
                numberStack.push(Integer.valueOf(expression.substring(i, j)));
                break;
            }
        }
        while (!operatorStack.isEmpty()){
            int result = operate((int)numberStack.pop(), (int)numberStack.pop(), (char) operatorStack.pop());
            numberStack.push(result);
        }
        return (int)numberStack.pop();
    }

    /**
     * 中缀转后缀
     * @param expression 中缀表达式
     * @return 后缀表达式
     *
     * 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
     */
    public static String infix2Suffix(String expression){
        if (expression == null || "".equals(expression)){
            throw new RuntimeException("表达式为空！");
        }
        List<String> list = new ArrayList<>();
        char[] elements = expression.toCharArray();

        //获取中缀表达式list
        for (int i = 0; i < elements.length; i++) {
            if (isOperator(elements[i]) || '(' == elements[i] || ')' == elements[i]){
                list.add("" + elements[i]);
                continue;
            }
            int j = i;
            try {
                while (!isOperator((elements[j])) && '(' != elements[j] && ')' != elements[j]){
                    j++;
                }
                list.add(expression.substring(i, j));
                i = j - 1;
            } catch (Exception e) {
                list.add(expression.substring(i, j));
                break;
            }
        }
        //infixList => suffixList
        //运算符栈
        Stack<String> stack = new Stack<>();
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            //若元素为数则加入suffix
            if (list.get(i).matches("\\d+")){
                suffix.append(list.get(i)).append(" ");
            } else {
                //若元素为"("或栈为空或元素优先级大于栈顶元素优先级则入栈
                if ("(".equals(list.get(i)) || stack.empty() || priority(list.get(i).charAt(0)) > priority(stack.peek().charAt(0))) {
                    stack.push(list.get(i));
                    continue;
                }
                //若元素为")"则依次pop出stack的元素直到遇到"("为止，且丢弃"("
                if (")".equals(list.get(i))) {
                    while (!"(".equals(stack.peek())) {
                        suffix.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                    continue;
                }
                suffix.append(stack.pop().charAt(0)).append(" ");
                if (stack.empty()){
                    i--;
                }
            }
        }
        while (!stack.empty()){
            suffix.append(stack.pop().charAt(0)).append(" ");
        }
        suffix.deleteCharAt(suffix.length() - 1);
        return String.valueOf(suffix);
    }

    /**
     * 逆波兰计算器
     *
     * @param expression 中缀表达式
     * (3+4)*5-6 => 3 4 + 5 * 6 -
     */
    public static int polandNotation(String expression){
        ArrayList<String> list = new ArrayList<>(Arrays.asList(infix2Suffix(expression).split(" ")));
        Stack<String> stack = new Stack<>();

        list.forEach(element -> {
            //匹配多位数
            if (element.matches("\\d+")){
                //若是数入栈
                stack.push(element);
            } else {
                //若是操作符，pop两个数并运算且将结果push进stack
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                switch (element){
                    case "+":stack.push(String.valueOf(num1 + num2));break;
                    case "-":stack.push(String.valueOf(num1 - num2));break;
                    case "*":stack.push(String.valueOf(num1 * num2));break;
                    case "/":stack.push(String.valueOf(num1 / num2));break;
                    default:throw new RuntimeException("operator error!");
                }
            }
        });

        return Integer.parseInt(stack.pop());
    }
}
