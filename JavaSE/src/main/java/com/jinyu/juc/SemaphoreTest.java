package com.jinyu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 13:38
 *
 * 在信号量上定义两种操作
 *      acquire获取：当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量-1），要么一直等待下去，直到有线程释放信号量，或超时
 *      release释放：将信号量+1，然后唤醒等待的线程
 * 信号量的目的
 *      用于多个共享资源的互斥使用
 *      用于并发线程数的控制
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //模拟资源类，3个停车位
        Semaphore semaphore = new Semaphore(3);
        //6辆汽车要停
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢占到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t离开了了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i + 1)).start();
        }
    }
}
