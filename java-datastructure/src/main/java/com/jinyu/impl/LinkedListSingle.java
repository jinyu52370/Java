package com.jinyu.impl;

import com.jinyu.LinkedList;

import java.util.Stack;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 20:53
 */
public class LinkedListSingle implements LinkedList {
    /**
     * 头结点
     */
    protected Node head = new Node(-1);

    public LinkedListSingle(Node head) {
        this.head = head;
    }

    public LinkedListSingle() {
    }

    /**
     * 尾添加
     */
    @Override
    public void rearAdd(Node node){
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
     * 按id顺序添加
     */
    @Override
    public void addByOrder(Node node){
        Node temp = head;
        boolean isIdExist = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.id > node.id){
                break;
            }
            if (temp.next.id == node.id){
                isIdExist = true;
                break;
            }
            temp = temp.next;
        }
        if (isIdExist){
            System.out.println("该id已存在：" + node.id);
        } else {
            node.next = temp.next;
            temp.next = node;
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
        Node temp = head;

        while (temp.next.id != id){
            temp = temp.next;
            if (temp.next == null){
                System.out.println("未找到编号为" + id + "的节点");
                return;
            }
        }
        temp.next = temp.next.next;
    }

    /**
     * 修改
     */
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

    /**
     * 打印链表
     */
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
        String returnMassage = (head.next == null) ? "链表为空" : (head.next.next == null ? "链表只有一个元素，无需返回" : null);
        if (returnMassage != null){
            System.out.println(returnMassage);
            return;
        }

        Node curr = head.next;
        //指向curr.next
        Node next;
        Node reverseHead = new Node(-1);

        while (curr != null){
            next = curr.next;
            curr.next = reverseHead.next;
            reverseHead.next = curr;
            curr = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 倒序打印链表
     */
    @Override
    public void reversePrint(){
        String returnMassage = (head.next == null) ? "链表为空" : (head.next.next == null ? "1" : "linkedList");
        switch (returnMassage){
            case "链表为空":{
                System.out.println(returnMassage);
                return;
            }
            case "1":{
                System.out.println(head.next);
                return;
            }
            default:break;
        }
        Stack<Node> stack = new Stack<>();
        Node temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 链表有效长度
     */
    @Override
    public int length(){
        int length = 0;

        if (head.next == null){
            System.out.println("链表为空");
            return length;
        }

        Node temp = head.next;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 合并链表且有序
     */
    public static LinkedListSingle merge(LinkedListSingle list1, LinkedListSingle list2){
        Node node1 = list1.head;
        Node node2 = list2.head;
        if (node1 == null){
            System.out.println("node1链表为空");
            return new LinkedListSingle();
        }
        if (node2 == null){
            System.out.println("node2链表为空");
            return new LinkedListSingle();
        }

        int[] ids = new int[list1.length() + list2.length()];
        int index = 0;
        //拼接node2到node1的尾部，并将id存放到ids数组
        Node temp = node1.next;
        while (temp.next != null) {
            ids[index] = temp.id;
            index++;
            temp = temp.next;
        }
        ids[index] = temp.id;
        index++;
        temp.next = node2.next;
        temp = node2.next;
        while (temp.next != null){
            ids[index] = temp.id;
            index++;
            temp = temp.next;
        }
        ids[index] = temp.id;

        int swap;
        for (int i = 0; i < ids.length - 1; i++) {
            for (int j = 0; j < ids.length - 1 - i; j++) {
                if (ids[j] > ids[j + 1]){
                    swap = ids[j];
                    ids[j] = ids[j + 1];
                    ids[j + 1] = swap;
                }
            }
        }
        //按ids顺序把节点加到新链表result上
        Node result = new Node(-1);
        Node resultTemp = result;
        for (int id : ids) {
            temp = node1;
            while (temp.next != null) {
                if (id == temp.next.id){
                    resultTemp.next = temp.next;
                    resultTemp = resultTemp.next;
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
        return new LinkedListSingle(result);
    }
}
