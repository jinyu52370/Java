package com.jinyu.designpattern.factory.bmwfactory;

import com.jinyu.designpattern.factory.BMW;
import com.jinyu.designpattern.factory.BMW5;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/18 11:50
 */
public class BMW5Factory implements BMWFactory {

    @Override
    public BMW productBMW() {
        System.out.println("生产宝马5系");
        return new BMW5();
    }
}
