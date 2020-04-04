package com.jinyu.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2019/12/10 21:55
 */
public class UserData {
    public static List<User> getAllUser(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"jjj1",212));
        users.add(new User(22,"jjj2",22));
        users.add(new User(-2,"jjj3",22));
        users.add(new User(4,"jjj4",1));
        users.add(new User(5,"jjj5",-20));

        return users;
    }
}
