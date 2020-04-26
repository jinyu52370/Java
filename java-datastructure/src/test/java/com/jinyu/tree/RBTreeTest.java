package com.jinyu.tree;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/26 21:25
 */
public class RBTreeTest {
    @Test
    public void test1(){
        RBTree rbTree = new RBTree();

        for (int i = 0; i < 3; i++) {
            rbTree.add(i + 1, (char)(97 + i));
        }
        rbTree.preOrder();
    }
}
