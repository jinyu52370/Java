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

    @Test
    public void arrBinaryTreeTest(){
        ArrBinaryTree tree = new ArrBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        tree.preOrder();
    }

    @Test
    public void threadedBinaryTreeTest(){
        //region private
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        TreeNode node10 = new TreeNode(10);
        TreeNode node14 = new TreeNode(14);

        node1.left = node3;
        node1.right = node6;
        node3.left = node8;
        node3.right = node10;
        node6.left = node14;

        ThreadedBinaryTree tree = new ThreadedBinaryTree();

        tree.setRoot(node1);
        //endregion

        tree.infixThreadedNode();
//
//        System.out.println(node10.left + "\t" + node10.leftType);
//        System.out.println(node10.right + "\t" + node10.rightType);

        tree.queryAll();
    }
}
