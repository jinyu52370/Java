package com.jinyu.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/27 19:48
 *
 * 动态代理类
 */
public class ProxyDemo implements InvocationHandler {
    /**
     * 被代理对象
     */
    private Object obj;

    public ProxyDemo(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {

        System.out.println(method.getName() + "\t开始执行");
        Object result = method.invoke(this.obj, args);
        System.out.println(method.getName() + "\t执行完毕");

        return result;
    }
}
