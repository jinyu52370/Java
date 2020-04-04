package com.jinyu;

import com.jinyu.entity.User;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/13 10:59
 */
public class MainTest {
    public static void main(String[] args) {
        Arrays.stream(args).forEach(System.out::println);

        User user = new User();
        System.out.println();
        User user1 = new User();
    }
}
