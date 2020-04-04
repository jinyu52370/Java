package com.jinyu.abstractpack;

import java.util.stream.Stream;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/13 11:58
 */
public class CodeExtends extends AbstractTimeTemplate{

    /**
     * 打印 0 ~ 1023
     */
    @Override
    public void code() {
        Stream.iterate(0, t -> t + 1).limit(1024).forEach(System.out::println);
    }
}
