package com.jinyu.designpattern.singleton;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/13 10:39
 *
 * 饿汉式
 */
public class EagerSingleton {
    /**
     * 私有构造：调用这个类时无法使用new来创建实例
     */
    private EagerSingleton(){}

    /**
     * 私有的Singleton类型的类变量
     */
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    public static EagerSingleton getInstance(){
        return eagerSingleton;
    }
}
