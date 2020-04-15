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

    private TreeNode preOrderQueryExecute(int id, TreeNode queryRoot){
        System.out.println("正在查找...当前节点id为：" + queryRoot.id);
        if (id == queryRoot.id){
            return queryRoot;
        }
        if (queryRoot.left != null) {
            TreeNode resultNode = preOrderQueryExecute(id, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (queryRoot.right != null) {
            return preOrderQueryExecute(id, queryRoot.right);
        }
        return null;
    }

    private TreeNode infixOrderQueryExecute(int id, TreeNode queryRoot){
        if (queryRoot.left != null) {
            TreeNode resultNode = preOrderQueryExecute(id, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        System.out.println("正在查找...当前节点id为：" + queryRoot.id);
        if (id == queryRoot.id){
            return queryRoot;
        }
        if (queryRoot.right != null) {
            return infixOrderQueryExecute(id, queryRoot.right);
        }
        return null;
    }

    private TreeNode postOrderQueryExecute(int id, TreeNode queryRoot) {
        TreeNode resultNode = null;
        if (queryRoot.left != null) {
            resultNode = preOrderQueryExecute(id, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (queryRoot.right != null) {
            resultNode = postOrderQueryExecute(id, queryRoot.right);
            if (resultNode != null){
                return resultNode;
            }
        }
        System.out.println("正在查找...当前节点id为：" + queryRoot.id);
        if (id == queryRoot.id){
            return queryRoot;
        }
        return null;
    }

    private boolean deleteExecute(int id, TreeNode queryRoot){
        if (queryRoot.left != null && id == queryRoot.left.id){
//            System.out.println("已删除节点" + queryRoot.left);
            queryRoot.left = null;
            return true;
        }
        if (queryRoot.right != null && id == queryRoot.right.id){
//            System.out.println("已删除节点" + queryRoot.right);
            queryRoot.right = null;
            return true;
        }
        if (queryRoot.left != null){
            boolean result = deleteExecute(id, queryRoot.left);
            if (result){
                return result;
            }
        }
        if (queryRoot.right != null){
            boolean result = deleteExecute(id, queryRoot.right);
            if (result){
                return result;
            }
        }
        return false;
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

    /**
     * 前序查找
     * @param id 需查找节点的id
     * @return 找到的节点
     */
    public TreeNode preOrderQuery(int id){
        return preOrderQueryExecute(id, root);
    }

    /**
     * 中序查找
     * @param id 需查找节点的id
     * @return 找到的节点
     */
    public TreeNode infixOrderQuery(int id){
        return infixOrderQueryExecute(id, root);
    }

    /**
     * 后序查找
     * @param id 需查找节点的id
     * @return 找到的节点
     */
    public TreeNode postOrderQuery(int id){
        return postOrderQueryExecute(id, root);
    }

    /**
     * 删除节点
     * @param id 需删除节点的id
     * @return 是否删除
     */
    public boolean delete(int id){
        if (id == root.id) {
//            System.out.println("已删除节点" + root);
            root = null;
            return true;
        }
        return deleteExecute(id, root);
    }
}
