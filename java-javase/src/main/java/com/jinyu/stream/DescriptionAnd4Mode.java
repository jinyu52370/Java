package com.jinyu.stream;

import com.jinyu.entity.UserData;
import com.jinyu.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description Stream 的描述和创建 stream 的方式
 * @date 2019/12/10 21:41
 *
 * 1.
 *  Stream
 *      关注对数据的运算 -> CPU
 *  Collection
 *      关注对数据的存储 -> 内存
 * 2.
 *  1> Stream 不会存储元素
 *  2> Stream 不会改变源对象，会返回一个持有结果的新Stream
 *  3> Stream 操作是延时的，等到需要结果时才执行
 *
 * 3.Stream执行流程
 *  1> Stream实例化
 *  2> 一系列的中间操作(过滤、映射、···)
 *      一个中间操作链，对数据源的数据进行处理
 *  3> 终止操作
 *      一旦执行终止操作，就执行中间操作链并产生结果。之后不可再使用
 *
 */
public class DescriptionAnd4Mode {
    /**
     * 创建Stream的方式一：集合
     */
    @Test
    public void test1(){
        List<User> users = UserData.getAllUser();

        //default Stream<E> stream() 返回一个顺序流
        Stream<User> stream = users.stream();

        //default Stream<E> parallelStream() 返回一个并行流
        Stream<User> parallelStream = users.parallelStream();


    }

    /**
     * 创建Stream的方式二：数组
     */
    @Test
    public void test2(){
        int[] array = {1,2,3,4,5};

        //调用Arrays类的 static <T> Stream<T> stream(T[] array) 返回一个流
        IntStream stream = Arrays.stream(array);

    }

    /**
     * 创建Stream的方式三：Stream的of()
     */
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

    }

    /**
     * 创建Stream的方式四：创建无限流
     */
    @Test
    public void test4(){
        /**
         * 迭代
         * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
         *
         * 遍历前10个偶数
         */
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        /**
         * 生成
         * public static<T> Stream<T> generate(Supplier<T> s)
         *
         *  生成10个随机数
         */
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
