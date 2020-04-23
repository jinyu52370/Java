package com.jinyu;

import org.junit.Test;

import java.sql.Timestamp;


/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/23 14:06
 */
public class DivideAndConquerTest {
    @Test
    public void hanoiTowerTest(){
        Timestamp t1 = new Timestamp(System.currentTimeMillis());
        DivideAndConquer.hanoiTower(10, 'a', 'b', 'c');
        System.out.println((new Timestamp(System.currentTimeMillis()).getTime() - t1.getTime()) / 1000.0);
    }
}
