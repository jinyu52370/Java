package com.jinyu.designpattern.singleton;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/13 10:48
 *
 * 懒汉式：初始对象为null，直到有第一个调用时再new
 */
public class LazySingleton {
    /**
     * 私有构造
     */
    private LazySingleton(){}

    /**
     * 私有的Singleton类型的类变量
     */
    private static LazySingleton lazySingleton = null;

    public static LazySingleton getInstance(){
        if (lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
