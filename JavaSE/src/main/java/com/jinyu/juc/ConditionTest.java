package com.jinyu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    /**
     * A:1  B:2 C:3
     */
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print(int cycleNumber){
        Condition condition = null;
        int waitFlag;

        switch (cycleNumber){
            case 5 : {
                waitFlag = 1;
                condition = c1;
                break;
            }
            case 10 : {
                waitFlag = 2;
                condition = c2;
                break;
            }
            case 15 : {
                waitFlag = 3;
                condition = c3;
                break;
            }
            default : throw new RuntimeException("无此循环次数(只可为5,10,15)");
        }

        lock.lock();
        try {
            while (flag != waitFlag){
                condition.await();
            }

            for (int i = 0; i < cycleNumber; i++) {
                System.out.println(Thread.currentThread().getName() + "：" + (i+1));
            }

            switch (flag){
                case 1 : {
                    flag = 2;
                    c2.signal();
                    break;
                }
                case 2 : {
                    flag = 3;
                    c3.signal();
                    break;
                }
                case 3 : {
                    flag = 1;
                    c1.signal();
                    break;
                }
                default: break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 *  多个线程之间按顺序调用，A->B->C
 *      A打印5次，B打印10次，C打印15次，循环10轮
 * @date 2020/3/30 9:55
 */
public class ConditionTest {
    public static void main(String[] args) {
        ShareData data = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.print(5);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.print(10);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.print(15);
            }
        }, "C").start();

    }
}
