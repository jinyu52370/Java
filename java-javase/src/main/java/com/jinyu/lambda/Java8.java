package com.jinyu.lambda;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description lambda 表达式
 * @date 2019/12/7 18:22
 */
public class Java8 {

    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("jjj啊啊啊111");
            }
        };

        r1.run();

        Runnable r2 = () -> System.out.println("jjj啊啊啊222");

        r2.run();
    }

    @Test
    public void test2(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = comparator.compare(12, 22);
        System.out.println(compare);

        Comparator<Integer> comparator1 = (o1,o2) -> Integer.compare(o1, o2);
        int compare1 = comparator1.compare(12, 22);
        System.out.println(compare1);

        Comparator<Integer> comparator2 = Integer::compare;
        int compare2 = comparator2.compare(12, 22);
        System.out.println(compare2);
    }

    /**
     * lambda 表达式
     *  1.格式
     *      -> lambda/箭头操作符
     *      (lambda形参列表) -> lambda体
     *  2.本质
     *      作为函数式接口的实例
     *  3.六种情况
     */
    @Test
    public void test3(){
        //一、无参，无返回
        Runnable run = () -> System.out.println("一、无参，无返回");
        run.run();

        //二、一参，无返回
//        Consumer<String> consumer = (String s) -> System.out.println(s);
        Consumer<String> consumer = System.out::println;
        consumer.accept("二、一参，无返回");

        //三、数据类型可省略，可由编译器推断得出，成为“类型推断”
            //1.
            Consumer<String> consumer1 = (s) -> System.out.println(s);
            consumer1.accept("三、数据类型可省略，可由编译器推断得出，成为“类型推断”");
            //2.
            List<Integer> list = new ArrayList<>();

        //四、lambda若只需要一个参数时，参数的小括号可省略
        Consumer<String> consumer2 = s -> System.out.println(s);
        consumer2.accept("四、lambda若只需要一个参数时，参数的小括号可省略");

        //五、lambda若需要两个及以上的参数，多条执行语句，并有返回值
        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(comparator.compare(111, 2));

        //六、lambda体只有一条语句时，return与大括号若有，均可省略
        Comparator<Integer> comparator1 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator1.compare(111, 2));

    }
}
