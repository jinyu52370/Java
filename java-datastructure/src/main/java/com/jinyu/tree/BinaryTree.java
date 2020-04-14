package com.jinyu.tree;

import com.jinyu.entity.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.reflect.generics.tree.Tree;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/14 16:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinaryTree {
    //region private
    protected TreeNode root;

    private void preOrderExecute(TreeNode node){
        System.out.println(node);
        if (node.left != null){
            preOrderExecute(node.left);
        }
        if (node.right != null){
            preOrderExecute(node.right);
        }
    }

    private void infixOrderExecute(TreeNode node){
        if (node.left != null){
            infixOrderExecute(node.left);
        }
        System.out.println(node);
        if (node.right != null){
            infixOrderExecute(node.right);
        }
    }

    private void postOrderExecute(TreeNode node){
        if (node.left != null){
            postOrderExecute(node.left);
        }
        if (node.right != null){
            postOrderExecute(node.right);
        }
        System.out.println(node);
    }

    private TreeNode preOrderQueryExecute(TreeNode node, TreeNode queryRoot){
        System.out.println("正在查找...");
        if (node.id == queryRoot.id){
            return queryRoot;
        }
        if (queryRoot.left != null) {
            TreeNode resultNode = preOrderQueryExecute(node, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (queryRoot.right != null) {
            return preOrderQueryExecute(node, queryRoot.right);
        }
        return null;
    }

    private TreeNode infixOrderQueryExecute(TreeNode node, TreeNode queryRoot){
        System.out.println("正在查找...");
        if (queryRoot.left != null) {
            TreeNode resultNode = preOrderQueryExecute(node, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (node.id == queryRoot.id){
            return queryRoot;
        }
        if (queryRoot.right != null) {
            return infixOrderQueryExecute(node, queryRoot.right);
        }
        return null;
    }

    private TreeNode postOrderQueryExecute(TreeNode node, TreeNode queryRoot) {
        System.out.println("正在查找...");
        TreeNode resultNode = null;
        if (queryRoot.left != null) {
            resultNode = preOrderQueryExecute(node, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (queryRoot.right != null) {
            resultNode = postOrderQueryExecute(node, queryRoot.right);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (node.id == queryRoot.id){
            return queryRoot;
        }
        return null;
    }
    //endregion

    //todo
    public void add(TreeNode node){
        if (root == null){
            root = node;
            return;
        }
        TreeNode temp = root;
        if (node.id < temp.id) {
            while (temp.left != null) {
                temp = temp.left;
            }
            temp.left = node;
            return;
        }
        if (node.id > temp.id) {
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = node;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if (root == null){
            System.out.println("树根节点为空");
            return;
        }
        preOrderExecute(root);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root == null){
            System.out.println("树根节点为空");
            return;
        }
        infixOrderExecute(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (root == null){
            System.out.println("树根节点为空");
            return;
        }
        postOrderExecute(root);
    }

    public TreeNode preOrderQuery(int id){
        return preOrderQueryExecute(new TreeNode(id), root);
    }

    public TreeNode infixOrderQuery(int id){
        return infixOrderQueryExecute(new TreeNode(id), root);
    }

    public TreeNode postOrderQuery(int id){
        return postOrderQueryExecute(new TreeNode(id), root);
    }
}
