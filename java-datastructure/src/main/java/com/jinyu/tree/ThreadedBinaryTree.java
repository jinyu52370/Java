package com.jinyu.tree;

import com.jinyu.entity.TreeNode;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/16 10:44
 *
 * 线索化二叉树
 */
public class ThreadedBinaryTree extends BinaryTree{
    //region private
    protected TreeNode pre = null;

    private void infixThreadedNode(TreeNode node) {
        if (node == null){
            return;
        }
        //线索化左子树
        infixThreadedNode(node.left);
        //线索化当前节点
        //处理前驱节点
        if (node.left == null){
            node.left = pre;
            node.leftType = 1;
        }
        //处理后继节点
        if (pre != null && pre.right == null){
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        //线索化右子树
        infixThreadedNode(node.right);
    }
    //endregion

    /**
     * 中序线索化
     */
    public void infixThreadedNode() {
        if (root == null){
            return;
        }
        this.infixThreadedNode(root);
    }

    /**
     * 线索化遍历
     */
    public void queryAll(){
        //中序线索化
        infixThreadedNode();
        TreeNode node = root;
        while (node != null) {
            //找到
            while (node.leftType == 0) {
                node = node.left;
            }
            System.out.println(node);
            while (node.rightType == 1){
                node = node.right;
                System.out.println(node);
            }
            node = node.right;
        }

    }
}
