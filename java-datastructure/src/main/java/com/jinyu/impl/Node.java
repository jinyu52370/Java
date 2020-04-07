package com.jinyu.impl;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 14:16
 */
public class Node {
    int id;
    Object[] objects;
    Node next = null;
    Node pre = null;

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }


    public Node(int id, Object... objects){
        this.id = id;
        this.objects = objects;
    }
}