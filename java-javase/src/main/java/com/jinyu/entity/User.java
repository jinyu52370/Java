package com.jinyu.entity;

import lombok.*;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2019/12/10 14:58
 */
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    int id;
    String name;
    int age;

    public User(){
        System.out.println("com.jjj.entity.ReflectUser()--------");
    }
}
