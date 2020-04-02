package com.jinyu.thread;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/28 9:56
 */
public class CodeMain1 {
    public static void main(String[] args) {
        Runnable run0 = new RunnableImpl();
        Runnable run1 = new RunnableImpl();

        Thread thread0 = new Thread(run0);
        Thread thread1 = new Thread(run1);

        thread0.start();
        thread1.start();

        /*
         * 创建Thread时若未指定名称，则系统默认为 Thread-?
         *
         * thread0.setName("thread set name 0");
         *
         * System.out.println(thread0.getName());
         * System.out.println(thread1.getName());
         *
         * 设置优先级，10最高，大概率优先执行优先级高的
         * thread1.setPriority(10);
         *
         * System.out.println(thread0.getPriority());
         * System.out.println(thread1.getPriority());
         */

        System.out.println("=========================1");
        System.out.println("=========================2");

        System.out.println(thread1.isAlive());
        thread1.stop();
        System.out.println(thread1.isAlive());

        try {
            //类似中断
            thread0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=========================3");
    }
}
