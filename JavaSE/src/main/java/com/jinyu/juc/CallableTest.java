package com.jinyu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("=====come in call method");
        Thread.sleep(3000);
        return 1024;
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/30 10:36
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask, "A").start();

        System.out.println("=====" + Thread.currentThread().getName() + " complete");

        System.out.println(futureTask.get());
    }
}
