package com.jinyu.linkedlist;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/6 22:52
 */
public class CycleLinkedList {
    private Node first;

    public CycleLinkedList(Node node){
        this.first = node;
        node.next = node;
    }

    public CycleLinkedList(){}

    public void add(Node node){
        //无节点则直接添加
        if (first == null){
            this.first = node;
            node.next = node;
            return;
        }
        Node curr = first;
        //curr移动到链表尾
        while (curr.next != first){
            curr = curr.next;
        }
        curr.next = node;
        node.next = first;
    }

    public void print(){
        //无节点返回打印null
        if (first == null){
            System.out.println("null");
            return;
        }
        //若只有一个节点直接打印
        Node curr = first;
        if (curr.next == first){
            System.out.println(curr);
            return;
        }
        //遍历打印
        while (curr.next != first){
            System.out.println(curr);
            curr = curr.next;
        }
        //打印链表尾
        System.out.println(curr);
    }

    public static void joseph(int startId, int count, int length){
        //参数校验
        if (startId < 1 || length < 0 || startId > length){
            System.out.println("参数错误");
            return;
        }
        //初始化链表
        CycleLinkedList list = new CycleLinkedList();
        for (int i = 1; i <= length; i++) {
            list.add(new Node(i));
        }
        Node first = list.first;
        //找到指定id的节点作为链表首
        while (first.id != startId){
            first = first.next;
        }
        //找到链表尾
        Node rear = first;
        while (rear.next != first){
            rear = rear.next;
        }
        //开始根据报数count出队
        System.out.print("[");
        while (rear != first) {
            for (int i = 0; i < count - 1; i++) {
                first = first.next;
                rear = rear.next;
            }
            System.out.print(first.id + ", ");
            first = first.next;
            rear.next = first;
        }
        System.out.print(first.id + "]");
    }
}
