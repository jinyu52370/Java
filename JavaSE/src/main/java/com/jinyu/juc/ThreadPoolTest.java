package com.jinyu.juc;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 9:22
 */
public class ThreadPoolTest {
    @Test
    public void test(){
        //（固定）执行长期任务性能好，创建一个线程池，一池有N个固定的线程，有固定的线程数的线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //（一个）single
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //（N个）可扩容
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            //10个请求
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    @Test
    public void test1(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            //10个请求
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
