package com.jinyu.dynamicproxy;

import org.junit.Test;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/27 19:45
 */
public class CodeTest {
    /**
     * 若一个对象想要通过Proxy.newProxyInstance(,,)方法代理，则这个对象必须要有相应的接口
     *  例： 接口：TestInterface
     *      实现：TestImpl
     */
    @Test
    public void test(){
        TestInterface test = new TestImpl();
//        test.method1();
//        test.method2();
//        test.method3();

        /*
         * 需求：执行method1、method2、method3前打印method开始执行，之后打印执行完毕
         *  Proxy.newProxyInstance(ClassLoader, interface, handler);
         *      参数1：代理对象的类加载器
         *      参数2：被代理对象的接口
         *      参数3：代理对象
         *
         *      返回值：成功被代理的对象
         */
        InvocationHandler handler = new ProxyDemo(test);
        TestInterface testProxyComplete = (TestInterface) Proxy.newProxyInstance(
                handler.getClass().getClassLoader(), test.getClass().getInterfaces(), handler);

        testProxyComplete.method1();
        System.out.println();
        testProxyComplete.method2();
        System.out.println();
        testProxyComplete.method3();
    }
}
