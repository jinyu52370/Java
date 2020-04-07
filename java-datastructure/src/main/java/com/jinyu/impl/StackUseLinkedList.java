package com.jinyu.impl;

import com.jinyu.Stack;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 12:41
 */
public class StackUseLinkedList implements Stack {
    private Node head = new Node(-1);

    public StackUseLinkedList(){

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty(){
        return head.next == null;
    }

    @Override
    public void push(Object[] objects){
//        if (isFull()){
//            return;
//        }
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        int idNum = temp.id + 1;

        Node node = new Node(idNum, objects);
        Node curr = head;
        node.next = curr.next;
        curr.next = node;
    }

    @Override
    public Object[] pop(){
        if (isEmpty()){
            return null;
        }
        Node curr = head;
        Object[] objects = curr.next.objects;
        curr.next = curr.next.next;
        return objects;
    }

    @Override
    public void print(){
        Node curr = head;
        System.out.print("[");
        while (curr.next != null){
            curr = curr.next;
            System.out.print("[");
            for (int j = 0; j < curr.objects.length; j++) {
                System.out.print(curr.objects[j]);
                if (j != curr.objects.length){
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (curr.next != null){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
