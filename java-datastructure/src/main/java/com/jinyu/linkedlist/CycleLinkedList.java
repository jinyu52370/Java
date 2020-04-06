package com.jinyu.linkedlist;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/6 22:52
 */
public class CycleLinkedList {
    private Node first;

    public CycleLinkedList(Node first){
        this.first = first;
        first.next = first;
    }

    public void add(Node node){
        Node curr = first;
        while (curr.next != first){
            curr = curr.next;
        }
        curr.next = node;
        node.next = first;
    }

    public void print(){
        Node curr = first;
        if (curr.next == first){
            System.out.println(curr);
            return;
        }
        while (curr.next != first){
            System.out.println(curr);
            curr = curr.next;
        }
        System.out.println(curr);
    }
}
