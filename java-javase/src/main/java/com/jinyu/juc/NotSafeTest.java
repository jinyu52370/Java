package com.jinyu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/29 15:51
 */
public class NotSafeTest {
    /**
     * 1. 故障现象
     *      java.util.ConcurrentModificationException
     * 2. 导致原因
     *
     * 3. 解决方法
     *      3.1 （不可使用）new Vector<>()
     *      3.2 （不可使用）Collections.synchronizedList(new ArrayList<>())
     *      3.3 new CopyOnWriteArrayList<>()
     * 4. 优化建议
     *
     */
    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName() + "\t\t" + list);
                }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName() + "\t\t" + set);
            }, String.valueOf(i)).start();
        }
    }

    private static void mapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t\t" + map);
            }, String.valueOf(i)).start();
        }
    }

    public static void main(String[] args) {
        mapNotSafe();
    }
}
