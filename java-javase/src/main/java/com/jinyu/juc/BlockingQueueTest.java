package com.jinyu.juc;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 17:18
 *
 * queue空：获取元素的操作被阻塞
 * queue满：添加元素的操作被阻塞
 *
 * BlockingQueue接口的实现类
 *      (√) ArrayBlockingQueue：由数组结构组成的有界阻塞队列。
 *      (√) LinkedBlockingQueue：由链表结构组成的有界（但大小默认值为 Integer. MAX_ VALUE）阻塞队列。
 *          PriorityBlockingQueue：支持优先级排序的无界阻塞队列。
 *          DelayQueue：使用优先级队列实现的延迟无界阻塞队列。
 *      (√) SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列。
 *          LinkedTransferQueue：由链表组成的无界阻塞队列
 *          LinkedBlockingDeque：由链表组成的双向阻塞队列。
 */
public class BlockingQueueTest {
    /**
     * capacity = 3
     */
    private ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

    @Test
    public void throwsException(){
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        /*
//         * Exception in thread "main" java.lang.IllegalStateException: Queue full
//         * System.out.println(blockingQueue.add("x"));
//         */
//
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        /*
//         * Exception in thread "main" java.util.NoSuchElementException
//         * System.out.println(blockingQueue.remove());
//         */

        System.out.println(blockingQueue.add("x"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.element());
    }

    @Test
    public void specialValue() {
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        /*
         * false
         * System.out.println(blockingQueue.offer("x"));
         */

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        /*
         * null
         * System.out.println(blockingQueue.poll());
         */

        System.out.println(blockingQueue.peek());
    }

    @Test
    public void blocking() throws InterruptedException {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        /*
         * blocking
         * blockingQueue.put("x");
         */

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        /*
         * blocking
         * System.out.println(blockingQueue.take());
         */
    }

    @Test
    public void timeout() throws InterruptedException{
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        /*
         * 等待 3 秒，offer失败返回false
         * System.out.println(blockingQueue.offer("x", 3L, TimeUnit.SECONDS));
         */

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        /*
         * 等待 3 秒，poll失败返回null
         * System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
         */
    }
}
