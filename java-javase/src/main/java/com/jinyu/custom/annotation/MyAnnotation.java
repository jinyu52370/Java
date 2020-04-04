package com.jinyu.custom.annotation;

import java.lang.annotation.*;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/25 23:27
 */
//作用范围：属性
@Target(ElementType.FIELD)
//生命周期
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyAnnotation {
    public int id() default 0;
    public String describe() default "";
}
