package com.jinyu.stack;

import com.jinyu.entity.Node;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/7 12:41
 */
public class StackUseLinkedList<T> implements Stack<T> {
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
    public void push(T t){
//        if (isFull()){
//            return;
//        }
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        int idNum = temp.id + 1;

        Node node = new Node(idNum, t);
        Node curr = head;
        node.next = curr.next;
        curr.next = node;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop(){
        if (isEmpty()){
            return null;
        }
        Node curr = head;
        T t = (T)curr.next.objects[0];
        curr.next = curr.next.next;
        return t;
    }

    @Override
    public void print(){
        Node curr = head;
        System.out.print("[");
        while (curr.next != null){
            curr = curr.next;
            System.out.println(curr.objects[0]);
            if (curr.next != null){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        return (T) head.next.objects[0];
    }
}
