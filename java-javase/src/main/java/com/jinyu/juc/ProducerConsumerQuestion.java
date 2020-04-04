package com.jinyu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //region old
//    public synchronized void increment() throws Exception{
//        while (number != 0) {
//            this.wait();
//        }
//        number++;
//        System.out.println(Thread.currentThread().getName() + "\t生产\t" + number);
//        this.notify();
//
//    }
//
//    public synchronized void decrement() throws Exception{
//        while (number == 0) {
//            this.wait();
//        }
//        number--;
//        System.out.println(Thread.currentThread().getName() + "\t消费\t" + number);
//        this.notify();
//    }
    //endregion

    public void increment() throws Exception{
        lock.lock();
        try {
            //1. 判断
            while (number != 0) {
                condition.await();
            }
            //2. 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t生产\t" + number);
            //3. 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            //1. 判断
            while (number == 0) {
                condition.await();
            }
            //2. 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t消费\t" + number);
            //3. 通知
            condition.signalAll();
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
 *      1. 高内聚低耦合前提下，线程操作资源类
 *      2. 判断/干活/通知
 *      3. 防止虚假唤醒
 * @date 2020/3/29 22:47
 */
public class ProducerConsumerQuestion {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}
