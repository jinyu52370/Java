package com.jinyu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int ticketNum = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock();
        try {
            if (ticketNum > 0){
                System.out.println(Thread.currentThread().getName()
                        + "\t卖出第：" + (ticketNum--) + "张票"
                        + "还剩" + ticketNum+ "张票");
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
 *  三个售票员   卖出  30张票
 * @date 2020/3/29 13:19
 */
public class TicketQuestion {
    /**
     * 主线程
     *  线程  /   操作  /   资源类
     */
    public static void main(String[] args) {

        final Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "售票员1").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "售票员2").start();

    }
}
