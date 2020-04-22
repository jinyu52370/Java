package com.jinyu.tree;

import com.jinyu.entity.TreeNode;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/21 21:48
 *
 * AVL 平衡二叉树
 */
public class SelfBalancingBinarySearchTree extends BinarySortTree{
    //region constructor
    public SelfBalancingBinarySearchTree(int[] array) {
        super(array);
    }

    public SelfBalancingBinarySearchTree() {
    }
    //endregion

    private int treeDepth(TreeNode node){
        return Math.max(node.left == null ? 0 : treeDepth(node.left), node.right == null ? 0 : treeDepth(node.right)) + 1;
    }

    public int rootTreeDepth(){
        if (root == null){
            return 0;
        }
        return treeDepth(root);
    }

    public int leftSubTreeDepth(TreeNode node){
        if (node == null || node.left == null){
            return 0;
        }
        return treeDepth(node.left);
    }

    public int rootLeftSubTreeDepth(){
        return leftSubTreeDepth(root);
    }

    public int rightSubTreeDepth(TreeNode node){
        if (node == null || node.right == null){
            return 0;
        }
        return treeDepth(node.right);
    }

    public int rootRightSubTreeDepth(){
        return rightSubTreeDepth(root);
    }

    private void leftRotate(TreeNode node){
        TreeNode newNode = new TreeNode(node.id);
        newNode.left = node.left;
        newNode.right = node.right.left;
        node.id = node.right.id;
        node.right = node.right.right;
        node.left = newNode;
    }

    private void rightRotate(TreeNode node){
        TreeNode newNode = new TreeNode(node.id);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.id = node.left.id;
        node.left = node.left.left;
        node.right = newNode;
    }

    private void create(TreeNode node, TreeNode root) {
        if (node.getId() <= root.getId()) {
            if (root.left == null) {
                root.left = node;
                return;
            }
            create(node, root.left);
        }
        if (node.getId() > root.getId()) {
            if (root.right == null) {
                root.right = node;
                return;
            }
            create(node, root.right);
        }

        if (rightSubTreeDepth(root) - leftSubTreeDepth(root) > 1) {
            if (root.right != null && leftSubTreeDepth(root.right) > rightSubTreeDepth(root.right)) {
                rightRotate(root.right);
            }
            leftRotate(root);
            return;
        }

        if (leftSubTreeDepth(root) - rightSubTreeDepth(root) > 1) {
            if (root.left != null && rightSubTreeDepth(root.left) > leftSubTreeDepth(root.left)) {
                leftRotate(root.left);
            }
            rightRotate(root);
        }
    }

    @Override
    public void create(int[] array) {
        root = new TreeNode(array[0]);
        for (int i = 1; i < array.length; i++) {
            create(new TreeNode(array[i]), root);
        }

    }

    @Override
    public void add(int[] array) {
        for (int a : array) {
            add(a);
        }
    }

    @Override
    public void add(int value) {
        create(new TreeNode(value), root);
    }
}
