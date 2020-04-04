package com.jinyu;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Arrays;

class Node{
    Object[] object;
    Node next;

    @Override
    public String toString() {
        return "Node{" +
                "object=" + Arrays.toString(object) +
                '}';
    }

    Node(Object... obj){
        this.object = obj;
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 20:53
 */
public class SingleLinkedList {
    /**
     * 头结点
     */
    private Node head = new Node(0,"","");

    /**
     * 添加节点
     *
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新节点
     */
    public void add(Node node){
        //头结点不能动
        Node temp = head;
        //遍历链表
        while (true){
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 打印链表
     */
    public void print(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }
}
