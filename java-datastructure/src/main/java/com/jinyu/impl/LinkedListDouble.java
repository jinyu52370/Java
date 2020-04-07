package com.jinyu.impl;

import com.jinyu.LinkedList;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/6 15:21
 */
public class LinkedListDouble implements LinkedList {
    protected Node head = new Node(-1);

    public LinkedListDouble(Node head) {
        this.head = head;
    }

    public LinkedListDouble() {
    }

    /**
     * 尾添加
     */
    @Override
    public void rearAdd(Node node){
        //头结点不能动
        Node temp = head;
        //遍历链表
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 头添加
     */
    public void frontAdd(Node node){
        Node temp = head;
        if (temp.next != null) {
            temp.next.pre = node;
            node.next = temp.next;
        }
        node.pre = temp;
        temp.next = node;
    }

    /**
     * 按id顺序添加
     */
    @Override
    public void addByOrder(Node node){
        Node temp = head;
        while (temp != null){
            if (temp.id == node.id){
                System.out.println("该id已存在：" + node.id);
                return;
            }
            if (temp.id > node.id){
                temp.pre.next = node;
                node.pre = temp.pre;
                node.next = temp;
                temp.pre = node;
                return;
            }
            if (temp.next == null){
                temp.next = node;
                node.pre = temp;
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除
     */
    @Override
    public void delete(int id){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        Node temp = head.next;
        while (temp != null){
            if (temp.id == id) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到编号为" + id + "的节点");
    }

    @Override
    public void update(Node node){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = head;
        while (temp.next.id != node.id){
            temp = temp.next;
            if (temp.next == null){
                System.out.println("未找到编号为" + node.id + "的节点");
                return;
            }
        }
        temp.next.objects = node.objects;
    }

    @Override
    public void print(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }

    }

    /**
     * 反转链表
     */
    @Override
    public void reverse(Node head){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        if (head.next.next == null){
            System.out.println("链表只有一个元素，无需返回");
            return;
        }
        Node temp = head.next;
        while (temp.next != null){
            temp = temp.next;
        }
        Node reverseHead = new Node(-1);
        Node reverseTemp = reverseHead;

        while (temp.pre != null){
            reverseTemp.next = temp;
            temp = temp.pre;
            temp.next.pre = reverseTemp;
            temp.next = null;
            reverseTemp = reverseTemp.next;
        }
        head.next = reverseHead.next;
        reverseHead.next.pre = head;
    }

    /**
     * 倒序打印链表
     */
    @Override
    public void reversePrint(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        if (head.next.next == null){
            System.out.println(head.next);
            return;
        }
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        while (temp.pre != null){
            System.out.println(temp);
            temp = temp.pre;
        }
    }

    @Override
    public int length() {
        return 0;
    }

}
