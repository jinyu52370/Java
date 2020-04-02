package com.jinyu.jvm;

class MyNumber{
    volatile int number = 10;

    public void addTo1024(){
        this.number = 1024;
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 9:26
 *
 * JMM：可见性（通知机制）
 */
public class JMMTest {
    public static void main(String[] args) {
        MyNumber number = new MyNumber();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                number.addTo1024();
                System.out.println(Thread.currentThread().getName() + " update number, value:" + number.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "MyNumber-thread").start();

        while (number.number == 10){
            //需要有一种通知机制(volatile)告诉main线程，number已经修改为1024，跳出while
        }

        System.out.println(Thread.currentThread().getName() + " mission over");
    }
}
