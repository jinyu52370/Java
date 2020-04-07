package com.jinyu.impl;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 23:54
 */
public class LinkedListTest {
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

    @Test
    public void singleLinkedListTest1(){
        LinkedListSingle linkedList = new LinkedListSingle();
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
    public void singleLinkedListTest2(){
        LinkedListSingle linkedList1 = new LinkedListSingle();
        linkedList1.rearAdd(node1);
        linkedList1.rearAdd(node2);
        linkedList1.rearAdd(node3);
        linkedList1.rearAdd(node4);
        linkedList1.rearAdd(node5);

        LinkedListSingle linkedList2 = new LinkedListSingle();
        linkedList2.rearAdd(node6);
        linkedList2.rearAdd(node7);
        linkedList2.rearAdd(node8);
        linkedList2.rearAdd(node9);

        LinkedListSingle linkedList3 = new LinkedListSingle();
        linkedList3.rearAdd(node10);
        linkedList3.rearAdd(node11);
        linkedList3.rearAdd(node12);
        linkedList3.rearAdd(node13);
        linkedList3.rearAdd(node14);
        linkedList3.rearAdd(node15);
        linkedList3.rearAdd(node16);


        LinkedListSingle mergeList1 = LinkedListSingle.merge(linkedList1, linkedList2);
        mergeList1.print();

        System.out.println();

        LinkedListSingle.merge(mergeList1, linkedList3).print();
    }

    @Test
    public void doubleLinkedListTest1(){
        LinkedListDouble list = new LinkedListDouble();

//        list.frontAdd(node1);
//        list.frontAdd(node2);
//        list.frontAdd(node3);
//        list.frontAdd(node4);
//        list.frontAdd(node5);

        list.addByOrder(node1);
        list.addByOrder(node2);
        list.addByOrder(node3);
        list.addByOrder(node4);
        list.addByOrder(node5);

//        list.delete(32);
//        list.update(new Node(1,"update","update1"));

        list.print();
    }

    @Test
    public void doubleLinkedListTest2(){
        LinkedListDouble list = new LinkedListDouble();

        list.addByOrder(node1);
        list.addByOrder(node2);
        list.addByOrder(node3);
        list.addByOrder(node4);
        list.addByOrder(node5);

        list.reverse(list.head);

        list.print();
    }

    @Test
    public void cycleLinkedListTest1(){
        Node nodea = new Node(1, "a");
        Node nodeb = new Node(2, "b");
        Node nodec = new Node(3, "c");
        Node noded = new Node(4, "d");
        Node nodee = new Node(5, "e");

        LinkedListCycle list = new LinkedListCycle();

        list.rearAdd(nodea);
        list.rearAdd(nodeb);
        list.rearAdd(nodec);
        list.rearAdd(noded);
        list.rearAdd(nodee);

        list.print();
    }

    @Test
    public void cycleLinkedListTest2(){
        LinkedListCycle.joseph(1, 3, 5);
    }
}
