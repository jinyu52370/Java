package com.jinyu.juc;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 14:38
 */
public class CompletableFutureTest {
    @Test
    public void runAsyncTest() throws Exception {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
                () -> System.out.println(Thread.currentThread().getName() + "没有返回, update mysql ok")
        );

        Void returnResult1 = completableFuture.get();
        System.out.println(returnResult1);
    }

    @Test
    public void supplyAsyncTest() throws Exception{
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回, insert mysql ok\n");
            int i = 1/0;
            return 1024;
        });

        Integer returnResult2 = completableFuture1.whenComplete((trueResult, exceptionResult) -> {
            System.out.println("*****trueResult\t=\t" + trueResult);
            System.out.println("*****exceptionResult\t=\t" + exceptionResult);
        }).exceptionally(f -> {
            System.out.println("*****exception:" + f.getMessage());
            return 444;
        }).get();

        System.out.println(returnResult2);
    }
}
