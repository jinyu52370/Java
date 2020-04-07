package com.jinyu;

import com.jinyu.impl.Node;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 14:35
 */
public interface LinkedList {
    /**
     * 尾添加
     */
    void rearAdd(Node node);

    /**
     * 按id顺序添加
     */
    void addByOrder(Node node);

    /**
     * 删除
     */
    void delete(int id);

    /**
     * 修改
     */
    void update(Node node);

    /**
     * 打印链表
     */
    void print();

    /**
     * 反转链表
     */
    void reverse(Node head);

    /**
     * 倒序打印链表
     */
    void reversePrint();

    /**
     * 链表有效长度
     */
    int length();
}
