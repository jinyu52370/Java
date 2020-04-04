package com.jinyu.thread;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/27 22:48
 */
public class RunnableImpl implements Runnable{
    private int count = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "多线程运行");
        for (int i=0;i<10;i++){

//            if ("Thread-0".equals(Thread.currentThread().getName())){
//                System.out.println(Thread.currentThread().getName() + "发生让步");
//                Thread.yield();
//            }

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            count++;
            System.out.println("线程\t"
                    + Thread.currentThread().getName()
                    + "：这是RunnableImpl的多线程的逻辑代码"
                    + i
                    + "\t"
                    + count);
        }
    }
}
