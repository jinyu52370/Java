package com.jinyu.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description Optional类 -- 为了在程序中避免出现空指针异常而创建的
 *                  常用方法：Optional.ofNullable(T t)
 *                          orElse(T other)
 * @date 2020/2/6 12:57
 */
public class OptionalTest {
    /**
     * Optional.of() -- the value to be present, which must be non-null
     */
    @Test
    public void test(){
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.of(girl);
    }

    /**
     * Optional.ofNullable() -- the possibly-null value to describe
     */
    @Test
    public void test1(){
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);

        /*
         * orElse(T t) -- 如果当前的Optional内部封装的t是非空的，则返回内部的t；
         * 若t是空的，则返回orElse()方法中的参数t1
         */
        System.out.println(optionalGirl.orElse(new Girl("girl orElse")));
    }

    /**
     * 会空指针的方法
     */
    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }

    /**
     * 空指针异常测试
     */
    @Test
    public void test2(){
        System.out.println(getGirlName(new Boy(new Girl())));
    }

    /**
     * 优化后的方法
     */
    public String getGirlNameOptimize(Boy boy){
        if (boy != null){
            Girl girl = boy.getGirl();
            if (girl != null){
                return girl.getName();
            }
        }
        return null;
    }

    /**
     * 优化后的空指针异常测试
     */
    @Test
    public void test3(){
        System.out.println(getGirlNameOptimize(new Boy(new Girl())));
    }

    /**
     * 使用Optional类的方法
     */
    public String getGirlNameByOptional(Boy boy){
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("boy is null")));

        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("girl is null"));

        return girl1.getName();
    }


    /**
     * 使用Optional类的空指针异常测试
     */
    @Test
    public void test4(){
        Boy boy = new Boy();
        Girl girl = new Girl();

//        boy = null;
//        girl = null;

        boy.setGirl(girl);

        System.out.println(getGirlNameByOptional(boy));
    }
}
