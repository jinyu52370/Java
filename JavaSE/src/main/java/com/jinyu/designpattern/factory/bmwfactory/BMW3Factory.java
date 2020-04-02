package com.jinyu.designpattern.factory.bmwfactory;

import com.jinyu.designpattern.factory.BMW;
import com.jinyu.designpattern.factory.BMW3;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/18 11:48
 */
public class BMW3Factory implements BMWFactory {

    @Override
    public BMW productBMW() {
        System.out.println("生产宝马3系");
        return new BMW3();
    }
}
