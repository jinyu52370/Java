package com.jinyu.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 11:07
 */
public class CountDownLatchTest {
    private static void closeDoor() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
            }, String.valueOf(i + 1)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i + 1)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }

}
