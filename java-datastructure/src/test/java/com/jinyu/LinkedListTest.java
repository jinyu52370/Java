package com.jinyu;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 23:54
 */
public class LinkedListTest {
    @Test
    public void test(){
        Node node1 = new Node(9, "宋江", "及时雨");
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node3 = new Node(11, "吴用", "智多星");
        Node node4 = new Node(1, "林冲", "豹子头");
        Node node5 = new Node(32, "李逵", "黑旋风");

        SingleLinkedList linkedList = new SingleLinkedList();
//        linkedList.add(node1);
//        linkedList.add(node2);
//        linkedList.add(node3);
//        linkedList.add(node4);

        linkedList.addByOrder(node1);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node5);

//        linkedList.update(new Node(1, "jjj", "jinyu"));
//
//        linkedList.delete(11);
//        linkedList.delete(1);
//        linkedList.delete(-1);
//        linkedList.delete(222);

        linkedList.print();

        System.out.println();

//        linkedList.reverse(linkedList.getHead());
        linkedList.reversePrint();
    }

    @Test
    public void test1(){
        Node node1 = new Node(9, "宋江", "及时雨");
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node3 = new Node(11, "吴用", "智多星");
        Node node4 = new Node(1, "林冲", "豹子头");
        Node node5 = new Node(32, "李逵", "黑旋风");

        Node node6 = new Node(3, "宋江1", "及时雨1");
        Node node7 = new Node(18, "卢俊义1", "玉麒麟1");
        Node node8 = new Node(100, "吴用1", "智多星1");
        Node node9 = new Node(88, "林冲1", "豹子头1");

        Node node10 = new Node(444, "jjj1");
        Node node11 = new Node(23, "jjj2");
        Node node12 = new Node(9880, "jjj3");
        Node node13 = new Node(4, "jjj4");
        Node node14 = new Node(87, "jjj5");
        Node node15 = new Node(53, "jjj6");
        Node node16 = new Node(666, "jjj7");

        SingleLinkedList linkedList1 = new SingleLinkedList();
        linkedList1.add(node1);
        linkedList1.add(node2);
        linkedList1.add(node3);
        linkedList1.add(node4);
        linkedList1.add(node5);

        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.add(node6);
        linkedList2.add(node7);
        linkedList2.add(node8);
        linkedList2.add(node9);

        SingleLinkedList linkedList3 = new SingleLinkedList();
        linkedList3.add(node10);
        linkedList3.add(node11);
        linkedList3.add(node12);
        linkedList3.add(node13);
        linkedList3.add(node14);
        linkedList3.add(node15);
        linkedList3.add(node16);


        SingleLinkedList merge1 = SingleLinkedList.merge(linkedList1, linkedList2);
        merge1.print();

        System.out.println();

        SingleLinkedList merge2 = SingleLinkedList.merge(merge1, linkedList3);
        merge2.print();
    }
}
