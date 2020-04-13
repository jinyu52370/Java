package com.jinyu.impl;

import org.junit.Test;



/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 17:21
 */
public class QueueTest {
    @Test
    public void arrayQueueTest(){
        QueueUseArray queue = new QueueUseArray(3);
        queue.add("jjj");
        queue.add(33.5);
        queue.add(2);

        queue.print();

    }

    @Test
    public void arrayCycleQueueTest(){
        QueueUseArrayCycle queue = new QueueUseArrayCycle(3);
        queue.add("jjj");
        queue.add(33.5);
        queue.add(2);

        queue.get();
        queue.add(3);

        queue.get();
        queue.add("x");

        queue.get();
        queue.add(666);

        queue.print();

    }

}
