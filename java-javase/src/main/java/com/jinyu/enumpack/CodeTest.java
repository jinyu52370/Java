package com.jinyu.enumpack;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 21:15
 */
public class CodeTest {
    @Test
    public void enumTest(){
        Season.SPRING.getInfo();
        Season.SUMMER.getInfo();
        Season.AUTUMN.getInfo();
        Season.WINTER.getInfo();

        System.out.println();

        Season spring = Season.SPRING;
        Season spring1 = Season.SPRING;

        //单例
        System.out.println(spring.equals(spring1));
    }
}
