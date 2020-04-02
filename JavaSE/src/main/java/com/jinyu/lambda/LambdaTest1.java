package com.jinyu.lambda;

import com.jinyu.entity.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2019/12/9 20:55
 */
public class LambdaTest1 {
    public void hhh(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    @Test
    public void test1(){
//        hhh(500, money -> System.out.println(money));
        hhh(500, System.out::println);
    }

    public List<String> filterString(List<String> str, Predicate<String> predicate){
        List<String> filter = new ArrayList<>();
        for (String s:str) {
            if (predicate.test(s)) {
                filter.add(s);
            }
        }
        return filter;
    }

    @Test
    public void test2(){
        List<String> array = Arrays.asList("北京","南京","东京","hhh");
        System.out.println(filterString(array, (s) -> s.contains("京")));
    }

    /**
     * 方法引用
     *  1.使用场景：当要传递给lambda体的操作，已经有实现的方法时，使用方法引用
     *  2.本质：函数式接口实例
     *  3.使用格式： 类/对象 :: 方法名
     *  4，三种情况
     *      1> 对象::非静态方法
     *      2> 类::静态方法
     *      3> 类::非静态方法
     *  5.要求：
     *      接口中的抽象方法进而方法引用的形参列表、返回值类型相同(针对于1,2)
     */
    @Test
    public void test3(){
        //对象::非静态方法
        PrintStream printStream = System.out;
        Consumer<String> consumer = printStream::println;
        consumer.accept("对象::非静态方法");

        //类::静态方法
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(111, 22));

        //类::非静态方法
//        Comparator<String> comparator1 = (s1,s2) -> s1.compareTo(s2);
        Comparator<String> comparator1 = String::compareTo;
        System.out.println(comparator1.compare("aaa","bbb"));
    }

    /**
     * 构造器引用
     * Supplier       T get()
     * User的空构造器   com.jjj.entity.ReflectUser()
     */
    @Test
    public void test4(){
        Supplier<User> supplier = () -> new User();
        supplier.get();

        Supplier<User> supplier1 = User::new;
        supplier1.get();
    }

    /**
     * 数组引用
     */
    @Test
    public void test5(){
        Function<Integer,String[]> function = length -> new String[length];
        System.out.println(Arrays.toString(function.apply(4)));

        Function<Integer,String[]> function1 = String[]::new;
        System.out.println(Arrays.toString(function1.apply(41)));
    }
}
