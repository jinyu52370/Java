package com.jinyu.juc;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {
    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (end + begin)/2;
            MyTask task1 = new MyTask(begin, middle);
            MyTask task2 = new MyTask(middle + 1, end);

            task1.fork();
            task2.fork();

            result = task1.join() + task2.join();
        }
        return result;
    }
}

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 13:55
 *
 * 分支合并框架
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 */
public class ForkJoinTest {
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        System.out.println(forkJoinTask.get());

        forkJoinPool.shutdown();
    }
}
