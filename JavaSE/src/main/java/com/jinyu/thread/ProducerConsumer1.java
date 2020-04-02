package com.jinyu.thread;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description 生产者消费者问题
 * @date 2020/3/28 12:37
 */
public class ProducerConsumer1 {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        //消费时不生产，生产时不消费
        new Thread(() -> {
            synchronized (clerk) {
                while (true) {
                    if (Clerk.productNum == 0) {
                        System.out.println("产品数为0，开始生产");
                        while (Clerk.productNum < 4) {
                            Clerk.productNum++;
                            System.out.println("库存：" + Clerk.productNum);
                        }
                        System.out.println("产品数为" + Clerk.productNum + "，结束生产");

                        //唤醒消费者线程
                        clerk.notify();
                    } else {
                        try {
                            //阻塞生产者线程
                            clerk.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "生产者").start();

        new Thread(() -> {
            synchronized (clerk) {
                while (true) {
                    if (Clerk.productNum == 4) {
                        System.out.println("产品数为4，开始消费");
                        while (Clerk.productNum > 0) {
                            Clerk.productNum--;
                            System.out.println("库存：" + Clerk.productNum);
                        }
                        System.out.println("产品数为" + Clerk.productNum + "，结束消费");

                        //唤醒生产者线程
                        clerk.notify();
                    } else {
                        try {
                            //阻塞消费者线程
                            clerk.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "消费者").start();
    }
}

class Clerk{
    public static int productNum = 0;
}