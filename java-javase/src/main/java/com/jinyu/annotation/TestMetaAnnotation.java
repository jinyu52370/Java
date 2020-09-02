package com.jinyu.annotation;

import org.junit.Test;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 *
 * Target：表示注解可以作用的地方
 * Retention：表示注解何处有效
 *    runtime > class > sources
 * Documented：表示将注解生成在JavaDoc中
 * Inherited：表示子类可以继承父类的注解
 */
@Target(value = {METHOD, TYPE})
@Retention(RUNTIME)
@Documented
@Inherited
@interface MyAnnotation{
    /**
     * 注解参数
     * 只有一个参数时，只有value可以在使用注解时省略
     */
    String value() default "";
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/7/4 21:30
 */
public class TestMetaAnnotation {
    @MyAnnotation
    @Test
    public void test1(){

    }
}
