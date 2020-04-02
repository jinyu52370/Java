package com.jinyu.thread;


/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 *  创建线程的两种方式
 *      1. 继承Thread类
 *          1> 定义子类继承Thread类
 *          2> 重写 run() 方法
 *          3> 创建了Tread子类对象，即创建了线程对象
 *          4> 调用线程对象 start()方法：启动线程，调用run()方法
 *      2. （常用）实现Runnable接口
 *
 * @date 2020/3/27 20:13
 */
public class CodeMain {
    public static void main(String[] args) {
        //region Thread

//        Thread thread1 = new ThreadChild();
//        Thread thread2 = new ThreadChild();
//        Thread thread3 = new ThreadChild();
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

        //endregion

//        Thread thread4 = new Thread(new RunnableImpl());
//        thread4.start();

//        Thread thread5 = new Thread(new RunnableImpl(), "thread-5");
//        Thread thread6 = new Thread(new RunnableImpl(), "thread-6");

        Runnable run = new RunnableImpl();

        Thread thread5 = new Thread(run, "thread-5");
        Thread thread6 = new Thread(run, "thread-6");

        thread5.start();
        thread6.start();

        System.out.println("====================");
        System.out.println("====================");
        System.out.println("====================");
    }
}
