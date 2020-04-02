package com.jinyu.thread;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/28 11:29
 */
public class CodeMain2 {
    public static void main(String[] args) {
        Account account = new Account();

        User userWeixin = new User(account, 2000);
        User userZhifubao = new User(account, 2000);

        Thread weixin = new Thread(userWeixin, "微信");
        Thread zhifubao = new Thread(userZhifubao, "支付宝");

        weixin.start();
        zhifubao.start();
    }
}

class User implements Runnable{
    Account account;
    int payMoney;

    public User(Account account, int payMoney){
        this.account = account;
        this.payMoney = payMoney;
    }

    @Override
    public void run() {
//        if ("微信".equals(Thread.currentThread().getName())){
//            account.payBlock(payMoney);
//        } else {
//            account.payBlock1(payMoney);
//        }

//        account.payStatic(payMoney);
//        account.payBlock(payMoney);

        account.pay(payMoney, account);
    }
}

class Account{
    private static int money = 3000;

    /**
     * 在普通方法上加同步锁synchronized，锁的是当前方法对应的对象，不是某个方法，当前对象所有加了同步锁的方法共用一个同步锁
     *      不同对象就是不同的锁，普通方法加synchronized，线程使用不同对象的此方法，还存在临界资源的问题
     * 静态方法加synchronized后，所有对象都是同一个锁
     * 代码块加入同步锁
     *      this 表示当前对象的代码块加了synchronized同步锁，若其他地方也使用synchronized(this)，则为同一个锁
     *      synchronized(obj) 传入不同的obj则有不同的锁
     */
    public synchronized void pay(int m, Account a){
        String name = Thread.currentThread().getName();

        if ("微信".equals(name)){
            try {
                a.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (money < m){
            System.out.println(name + "操作，余额" + money +"不足，无法支付!");
        } else {
            System.out.println(name + "操作，账户原有金额：" + money);
            System.out.println(name + "操作，取款金额：" + m);
            money -= m;
            System.out.println(name + "操作，账户取款后余额：" + money);
        }

        if ("支付宝".equals(name)){
            a.notify();
        }
    }

    public synchronized void pay1(int m){
        String name = Thread.currentThread().getName();

        if (money < m){
            System.out.println(name + "操作，余额" + money +"不足，无法支付!");
        } else {
            System.out.println(name + "操作，账户原有金额：" + money);
            System.out.println(name + "操作，取款金额：" + m);
            money -= m;
            System.out.println(name + "操作，账户取款后余额：" + money);
        }
    }

    public void payBlock(int m){
        /*
         * this 表示当前对象的代码块加了synchronized同步锁
         * 若其他地方也使用synchronized(this)，则为同一个锁
         */
        synchronized (this) {
            String name = Thread.currentThread().getName();
            if (money < m){
                System.out.println(name + "操作，余额" + money +"不足，无法支付!");
            } else {
                System.out.println(name + "操作，账户原有金额：" + money);
                System.out.println(name + "操作，取款金额：" + m);
                money -= m;
                System.out.println(name + "操作，账户取款后余额：" + money);
            }
        }
    }

    public void payBlock1(int m){
        synchronized (this) {
            String name = Thread.currentThread().getName();
            if (money < m){
                System.out.println(name + "操作，余额" + money +"不足，无法支付!");
            } else {
                System.out.println(name + "操作，账户原有金额：" + money);
                System.out.println(name + "操作，取款金额：" + m);
                money -= m;
                System.out.println(name + "操作，账户取款后余额：" + money);
            }
        }
    }

    public static synchronized void payStatic(int m){
        String name = Thread.currentThread().getName();

        if (money < m){
            System.out.println(name + "操作，余额" + money +"不足，无法支付!");
        } else {
            System.out.println(name + "操作，账户原有金额：" + money);
            System.out.println(name + "操作，取款金额：" + m);
            money -= m;
            System.out.println(name + "操作，账户取款后余额：" + money);
        }
    }
}
