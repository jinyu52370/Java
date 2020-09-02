package com.jinyu.tree;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/26 21:25
 */
public class RBTreeTest {
    @Test
    public void test1(){
        RBTree<Integer, Character> rbTree = new RBTree<>();

        for (int i = 0; i < 10; i++) {
            rbTree.add(i + 1, (char)(97 + i));
        }
//        int[] arr = {16, 3, 7, 11, 9, 26, 18, 14, 15};
//        for (int i : arr){
//            rbTree.add(i, i);
//        }

        rbTree.preOrder();

        System.out.println("\n");

        rbTree.infixOrder();

        System.out.println("\n");

        rbTree.postOrder();
    }
}
