package com.jinyu.juc;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws Exception{

        TimeUnit.SECONDS.sleep(4);
        System.out.println("===sendEmail====");
    }

    public synchronized void sendSMS() throws Exception{
        System.out.println("===sendSMS====");
    }

    public void sayHello() throws Exception{
        System.out.println("===sayHello====");
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 *  synchronized实现同步的基础：Java的每一个对象都可以作为锁
 *      对于普通同步方法，锁的是当前实例对象（对象锁）
 *      对于同步方法块，锁的是synchronized括号里配置的对象
 *      对于静态方法，锁的是当前类的Class对象（类锁）
 *
 *
 * 8 lock
 *  1. 标准访问，请问先打印邮件还是短信
 *  2. 暂停4秒，调用邮件方法，请问先打印邮件还是短信
 *      一个对象里若有多个synchronized方法，有一时刻内，只要一个线程去调用其中的一个synchronized方法，其他的线程都只能等待：
 *          即，某一时刻内，只能有唯一的一个线程去访问只写synchronized方法
 *          锁的是当前对象this（对象锁），被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
 *  3. 新增普通sayHello()方法，请问先打印邮件还是hello
 *      加普通方法后与同步锁无关
 *  4. 两部手机，请问先打印邮件还是短信
 *      换成两个对象后，不是同一把锁了
 *  5. 两个静态同步方法，同一部手机，请问先打印邮件还是短信
 *  6. 两个静态同步方法，两部手机，请问先打印邮件还是短信
 *      静态同步方法，锁的是当前类的Class对象（类锁）
 *  7. 一个静态同步方法，一个普通同步方法，同一部手机，请问先打印邮件还是短信
 *  8. 一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信
 *      静态同步方法和非静态同步方法不是同一个锁，不会有竞态条件
 *      一旦一个静态同步方法获取锁后，其他无论是否属于同一个实例的静态同步方法均需等待该方法释放锁后才能获取锁
 *
 * @date 2020/3/29 19:41
 */
public class Lock8Test {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                phone1.sendSMS();
//                phone.sayHello();
//                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

    }
}
