package com.jinyu.stream;

import com.jinyu.entity.UserData;
import com.jinyu.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description Stream终止操作
 * @date 2019/12/11 22:05
 */
public class TerminationOperation {
    //region 匹配与查找
    /**
     * 1-匹配与查找
     */
    @Test
    public void test(){
        List<User> users = UserData.getAllUser();

        /*
         * allMatch(Predicate p)--检查是否匹配所有元素
         *      检测是否所有用户的年龄都大于18
         */
        boolean allMatch = users.stream().allMatch(user -> user.getAge() > 18);
        System.out.println(allMatch + "\n");

        /*
         * anyMatch(Predicate p)--检测是否至少匹配一个元素
         *      检测是否存在用户的年龄小于0
         */
        boolean anyMatch = users.stream().anyMatch(user -> user.getAge() < 0);
        System.out.println(anyMatch + "\n");

        /*
         * noneMatch(Predicate p)--检测是否没有匹配的元素
         *      检测是否没有用户叫 今愚
         */
        boolean noneMatch = users.stream().noneMatch(user -> "今愚".equals(user.getName()));
        System.out.println(noneMatch + "\n");

        /*
         * findFirst--返回第一个元素
         */
        Optional<User> first = users.stream().findFirst();
        System.out.println(first + "\n");

        /*
         * findAny--返回当前流中的任意元素
         *      串行流Stream返回任意元素时总会取第一个
         */
        Optional<User> any = users.parallelStream().findAny();
        System.out.println(any + "\n");

        /*
         * count--返回流中的元素的总个数
         */
        System.out.println(users.stream().count() + "\n");

        /*
         * max(Comparator c)--返回流中的最大值
         *      找出所有用户最大的年龄
         */
        Optional<Integer> maxAge = users.stream().map(User::getAge).max(Integer::compare);
        System.out.println(maxAge + "\n");

        /*
         * min(Comparator c)--返回流中的最小值
         *      找出所有用户最小的id
         */
        System.out.println(users.stream().map(User::getId).min(Integer::compare) + "\n");

        /*
         * forEach(Consumer c)--内部迭代
         */
        users.stream().forEach(System.out::println);

        System.out.println();

        users.forEach(System.out::println);
    }
    //endregion

    //region 归约
    /**
     * 归约
     */
    @Test
    public void test1(){
        /*
         * reduce(T identity, BinaryOperator)--可以将流中元素反复结合起来，得到一个值。返回T
         *      计算1-10自然数的和
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum + "\n");


        /*
         * reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值。返回Optional<T>
         *     计算所有用户年龄总和
         */
        Optional<Integer> ageSum = UserData.getAllUser().stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(ageSum);
    }
    //endregion

    /**
     * 收集
     */
    @Test
    public void test2(){
        /*
         * collect(Collector c)--将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
         *      查找年龄大于18岁的用户，结果返回一个List或Set
         */
        //list
        List<User> collectUserList = UserData.getAllUser().stream().filter(user -> user.getAge() > 18).collect(Collectors.toList());
        collectUserList.forEach(System.out::println);

        System.out.println();
        //set
        Set<User> collectUserSet = UserData.getAllUser().stream().filter(user -> user.getAge() > 18).collect(Collectors.toSet());
        collectUserSet.forEach(System.out::println);

        /*
         *
         */
    }
}
