package com.jinyu.designpattern.factory;

import com.jinyu.designpattern.factory.bmwfactory.BMW3Factory;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/18 11:51
 */
public class MainTest {
    public static void main(String[] args) {
        BMW b3 = new BMW3Factory().productBMW();
    }
}