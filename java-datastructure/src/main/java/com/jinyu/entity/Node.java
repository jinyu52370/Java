package com.jinyu.entity;


/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 14:16
 */
public class Node extends BaseNode{

    public Node next = null;
    public Node pre = null;


    public Node(int id, Object... objects) {
        super(id, objects);
    }
}
