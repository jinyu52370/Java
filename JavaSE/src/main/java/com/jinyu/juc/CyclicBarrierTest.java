package com.jinyu.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 11:27
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //CyclicBarrier(int parties, Runnable barrierAction) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("===召唤神龙==="));

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + (temp + 1) + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i + 1)).start();
        }
    }
}
