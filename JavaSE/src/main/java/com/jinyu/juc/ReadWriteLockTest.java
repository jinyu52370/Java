package com.jinyu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入开始=====");

            TimeUnit.MILLISECONDS.sleep(300);

            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成=====");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取开始=====");

            TimeUnit.MILLISECONDS.sleep(300);

            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成：\t" + result + "=====");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 15:27
 *
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应当可以同时进行
 * 但若有一个线程想写临界资源，就不应该有其他的线程对该资源进行读或写
 *      R-R 可共享
 *      R-W 不可共享
 *      W-W 不可共享
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        final String key = "key";

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(key + tempInt, tempInt);
            }, "write" + String.valueOf(i + 1)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(key + tempInt);
            }, "read" + String.valueOf(i + 1)).start();
        }
    }

}
