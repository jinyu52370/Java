package com.jinyu;

import org.junit.Test;

import java.util.Date;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/3 17:39
 */
public class CodeTest {
    @Test
    public void instanceOfTest(){
        Date date = new Date();
        System.out.println(date instanceof Object);
    }
}

