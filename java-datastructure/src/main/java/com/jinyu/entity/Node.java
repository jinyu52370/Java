package com.jinyu.entity;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 14:16
 */
public class Node {
    public int id;
    public Object[] objects;
    public Node next = null;
    public Node pre = null;

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
