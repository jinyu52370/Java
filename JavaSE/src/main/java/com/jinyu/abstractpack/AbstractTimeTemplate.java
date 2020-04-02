package com.jinyu.abstractpack;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/13 11:45
 */
public abstract class AbstractTimeTemplate {
    /**
     * 子类具体的代码
     */
    public abstract void code();

    /**
     * 获得代码的执行时间
     */
    public final void getTime(){
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();

        System.out.println("code()执行时间：" + (end - start));
    }
}
