package com.jinyu.designpattern.singleton;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 20:13
 */
public class CodeTest {
    @Test
    public void singletonTest(){
        //饿汉
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();

        //懒汉
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();

        System.out.println(eager1.equals(eager2));
        System.out.println(lazy1.equals(lazy2));
    }
}
