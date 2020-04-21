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

    public int treeDepth(){
        if (root == null){
            return 0;
        }
        return treeDepth(root);
    }

    public int leftSubTreeDepth(){
        if (root == null || root.left == null){
            return 0;
        }
        return treeDepth(root.left);
    }

    public int rightSubTreeDepth(){
        if (root == null || root.right == null){
            return 0;
        }
        return treeDepth(root.right);
    }
}
