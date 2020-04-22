package com.jinyu.tree;

import com.jinyu.entity.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/1/14 16:48
 */
public class TreeTest {
    private BinaryTree init() {
        TreeNode node1 = new TreeNode(1, "宋江");
        TreeNode node2 = new TreeNode(2, "吴用");
        TreeNode node3 = new TreeNode(3, "卢俊义");
        TreeNode node4 = new TreeNode(4, "李逵");
        TreeNode node5 = new TreeNode(5, "林冲");

        BinaryTree tree = new BinaryTree();

        tree.root = node1;

        node1.left = node2;
        node1.right = node3;
        node3.left = node5;
        node3.right = node4;

        return tree;
    }

    private BinaryTree tree = init();


    @Test
    public void binaryTreeTest1() {
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
    public void arrBinaryTreeTest() {
        ArrBinaryTree tree = new ArrBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        tree.preOrder();
    }

    @Test
    public void threadedBinaryTreeTest() {
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

    @Test
    public void heapSortTest() {
        int[] sort = HeapSort.sort(new int[]{8, 7, 6, 5, 4, 3, 2, 1, -1});
        System.out.println(Arrays.toString(sort));
    }

    @Test
    public void huffmanTreeTest1() {
        List<TreeNode> treeNodes = Arrays.asList(
                new TreeNode(13),
                new TreeNode(7),
                new TreeNode(8),
                new TreeNode(3),
                new TreeNode(29),
                new TreeNode(6),
                new TreeNode(1)
        );
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.create(treeNodes);

        huffmanTree.preOrder();
    }

    @Test
    public void huffmanTreeTest2() {
        String str = "this is a huffman tree la la la, but it has some bugs :(";

        HuffmanTree huffmanTree = new HuffmanTree(str);

//        huffmanTree.preOrder();
//        System.out.println(huffmanTree.codeMap());
//        System.out.println(Arrays.toString(huffmanTree.huffmanBytes()));
//        System.out.println(huffmanTree.huffmanCodes());
        huffmanTree.write("F:/_IOFiles/", "huffman");
        System.out.println(huffmanTree.read("F:/_IOFiles/", "huffman"));
    }

    @Test
    public void huffmanTreeTest3() {
        HuffmanTree tree = new HuffmanTree();
        tree.compressFile("F:/_IOFiles/wdnmd.bmp", "F:/_IOFiles/wdnmd.zip");
        tree.unCompressFile("F:/_IOFiles/wdnmd.zip", "F:/wdnmd.bmp");
    }

    @Test
    public void binarySortTreeTest() {
        int[] array = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree tree = new BinarySortTree(array);

        tree.add(new int[]{100, 200, 300});

        tree.preOrder();
//        System.out.println(tree.query(10));
//        System.out.println(tree.queryParent(10));

        System.out.println("\n\n" + tree.delete(3) + "\n\n");

        tree.preOrder();
    }

    @Test
    public void AVLTest(){
//        int[] array = {4, 3, 6, 5, 7, 8};
//        int[] array = {10,12,8,9,7,6};
        int[] array = {10,11,7,6,8,9};
        SelfBalancingBinarySearchTree tree = new SelfBalancingBinarySearchTree(array);
        System.out.println(tree.rootTreeDepth());
        System.out.println(tree.rootLeftSubTreeDepth());
        System.out.println(tree.rootRightSubTreeDepth());

        tree.preOrder();
    }
}
