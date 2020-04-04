package com.jinyu.dynamicproxy;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/27 19:43
 */
public class TestImpl implements TestInterface {

    @Override
    public void method1() {
        System.out.println("执行method1");
    }

    @Override
    public void method2() {
        System.out.println("执行method2");
    }

    @Override
    public void method3() {
        System.out.println("执行method3");
    }
}
