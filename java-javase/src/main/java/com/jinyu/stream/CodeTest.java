package com.jinyu.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class User{
    private Integer id;
    private String name;
    private int age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 12:34
 */
public class CodeTest {
    @Test
    public void test(){
        User user1 = new User(11, "a", 23);
        User user2 = new User(12, "b", 24);
        User user3 = new User(13, "c", 22);
        User user4 = new User(14, "d", 28);
        User user5 = new User(16, "e", 26);

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        /*
         * 求id为偶数且年龄大于24岁的user的名字，按名字的字母倒序，只取第一个
         */
        users.stream()
                .filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 24)
                .sorted(Comparator.comparing(User::getName).reversed())
                .map(user -> user.getName().toUpperCase())
                .limit(1)
                .forEach(System.out::println);
    }
}
