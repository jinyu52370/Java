package com.jinyu.thread;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description 继承Thread的方式实现多线程
 * @date 2020/3/27 22:31
 */
public class ThreadChild extends Thread{
    @Override
    public void run() {
        System.out.println("多线程运行");
        for (int i=0;i<10;i++){
            System.out.println("这是ThreadChild的多线程的逻辑代码" + i);
        }
    }
}
