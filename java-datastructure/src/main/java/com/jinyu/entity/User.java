package com.jinyu.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/13 19:29
 */
@Data
public class User {
    private int id;
    private String name;

    @ToString.Exclude
    public User next;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
