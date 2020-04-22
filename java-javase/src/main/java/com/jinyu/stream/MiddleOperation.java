package com.jinyu.stream;

import com.jinyu.entity.UserData;
import com.jinyu.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description Stream中间操作
 * @date 2019/12/10 22:22
 */
public class MiddleOperation {
    /**
     * 1.筛选与切片
     */
    @Test
    public void test1(){
        List<User> users = UserData.getAllUser();

        /*
         * filter(Predicate p)--接受lambda，从流中排除某些元素
         *      过滤年龄大于22的用户
         */
        users.stream().filter(user -> user.getAge() > 22).forEach(System.out::println);

        /*
         * limit(n)--截断流，使其元素不超过给定的数量
         *      遍历用户信息且不多于3个
         */
        users.stream().limit(3).forEach(System.out::println);

        /*
         * skip(n)--跳过元素，返回一个扔掉了前n个元素流。若流中元素不足，则返回一个空流。与limit互补
         *      遍历用户信息且抛弃前3个
         */
        users.stream().skip(3).forEach(System.out::println);


        users.add(new User(111,"aaa",123123));
        users.add(new User(111,"aaa",123123));
        users.add(new User(111,"aaa",123123));
        users.add(new User(111,"aaa",123123));
        users.add(new User(111,"aaa",123123));

        /*
         * distinct--筛选，通过流所生成的元素的 hashCode() 和 equals() 去除重复元素
         */
        users.stream().distinct().forEach(System.out::println);
    }

    /**
     * 将字符串中的多个字符构成的集合转换为对应的Stream的实例
     */
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 2.映射
     */
    @Test
    public void test2(){
        /*
         * graph(Function f)--接收一个函数作为参数，将元素转换为其他形式或提取信息，该函数会被应用到每个元素上，并将其映射为一个新的流
         *      类比于list里的add()
         *      小写->大写
         */
        List<String> list = Arrays.asList("aaa", "bbb", "cccca", "dded");
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        /*
         * flatMap(Function f)--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
         *      类比于list里的addAll()
         */
        //test
        Stream<Stream<Character>> streamStream = list.stream().map(MiddleOperation::fromStringToStream);
        streamStream.forEach(s -> s.forEach(System.out::println));

        //flatMap
        Stream<Character> characterStream = list.stream().flatMap(MiddleOperation::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    /**
     * 3.排序
     */
    @Test
    public void test3(){
        //sorted()--自然排序
        List<Integer> list = Arrays.asList(13, 222, 34, -4, 11115);
        list.stream().sorted().forEach(System.out::println);

        System.out.println();

        /*
         * sorted(Comparator com)--定制排序
         *      根据用户年龄排序，若年龄相等则按id排序
         */
        List<User> users = UserData.getAllUser();
        users.stream().sorted((user1,user2) -> {
            int ageValue = Integer.compare(user1.getAge(), user2.getAge());
            if (ageValue != 0){
                return ageValue;
            } else {
                return Integer.compare(user1.getId(), user2.getId());
            }
        }).forEach(System.out::println);
    }
}
