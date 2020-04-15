package com.jinyu.tree;

import com.jinyu.entity.TreeNode;
import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/1/14 16:48
 */
public class TreeTest {
    private BinaryTree init(){
        TreeNode node1 = new TreeNode(1,"宋江");
        TreeNode node2 = new TreeNode(2, "吴用");
        TreeNode node3 = new TreeNode(3,"卢俊义");
        TreeNode node4 = new TreeNode(4,"李逵");
        TreeNode node5 = new TreeNode(5,"林冲");

        BinaryTree tree = new BinaryTree();

        tree.add(node1);

        node1.left = node2;
        node1.right = node3;
        node3.left = node5;
        node3.right = node4;

        return tree;
    }
    private BinaryTree tree = init();


    @Test
    public void binaryTreeTest1(){
        tree.preOrder();
        System.out.println();
        tree.infixOrder();
        System.out.println();
        tree.postOrder();
    }

    @Test
    public void binaryTreeTest2() {
        int id = 5;

        System.out.println(tree.preOrderQuery(id) + "\n\n");
        System.out.println(tree.infixOrderQuery(id) + "\n\n");
        System.out.println(tree.postOrderQuery(id));
    }

    @Test
    public void binaryTreeTest3() {
        System.out.println(tree.delete(2));

        tree.preOrder();
    }

}
