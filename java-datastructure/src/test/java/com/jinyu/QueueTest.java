package com.jinyu;

import org.junit.Test;



/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 17:21
 */
public class QueueTest {
    @Test
    public void arrayQueueTest(){
        ArrayQueue queue = new ArrayQueue(3);
        queue.add("jjj");
        queue.add(33.5);
        queue.add(2);

        queue.print();

    }

    @Test
    public void arrayCycleQueueTest(){
        ArrayCycleQueue queue = new ArrayCycleQueue(3);
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
