package com.jinyu.tree;

import com.jinyu.entity.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.reflect.generics.tree.Tree;

import java.io.Serializable;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/14 16:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinaryTree implements Serializable {
    //region private
    protected TreeNode root;

    private void preOrder(TreeNode node){
        System.out.println(node);
        if (node.left != null){
            preOrder(node.left);
        }
        if (node.right != null){
            preOrder(node.right);
        }
    }

    private void infixOrder(TreeNode node){
        if (node.left != null){
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null){
            infixOrder(node.right);
        }
    }

    private void postOrder(TreeNode node){
        if (node.left != null){
            postOrder(node.left);
        }
        if (node.right != null){
            postOrder(node.right);
        }
        System.out.println(node);
    }

    private TreeNode preOrderQuery(int id, TreeNode queryRoot){
        System.out.println("正在查找...当前节点id为：" + queryRoot.id);
        if (id == queryRoot.id){
            return queryRoot;
        }
        if (queryRoot.left != null) {
            TreeNode resultNode = preOrderQuery(id, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (queryRoot.right != null) {
            return preOrderQuery(id, queryRoot.right);
        }
        return null;
    }

    private TreeNode infixOrderQuery(int id, TreeNode queryRoot){
        if (queryRoot.left != null) {
            TreeNode resultNode = preOrderQuery(id, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        System.out.println("正在查找...当前节点id为：" + queryRoot.id);
        if (id == queryRoot.id){
            return queryRoot;
        }
        if (queryRoot.right != null) {
            return infixOrderQuery(id, queryRoot.right);
        }
        return null;
    }

    private TreeNode postOrderQuery(int id, TreeNode queryRoot) {
        TreeNode resultNode = null;
        if (queryRoot.left != null) {
            resultNode = preOrderQuery(id, queryRoot.left);
            if (resultNode != null){
                return resultNode;
            }
        }
        if (queryRoot.right != null) {
            resultNode = postOrderQuery(id, queryRoot.right);
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

    private boolean delete(int id, TreeNode queryRoot){
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
            boolean result = delete(id, queryRoot.left);
            if (result){
                return result;
            }
        }
        if (queryRoot.right != null){
            boolean result = delete(id, queryRoot.right);
            if (result){
                return result;
            }
        }
        return false;
    }
    //endregion

    /**
     * 前序遍历
     */
    public void preOrder(){
        if (root == null){
            System.out.println("树根节点为空");
            return;
        }
        preOrder(root);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root == null){
            System.out.println("树根节点为空");
            return;
        }
        infixOrder(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (root == null){
            System.out.println("树根节点为空");
            return;
        }
        postOrder(root);
    }

    /**
     * 前序查找
     * @param id 需查找节点的id
     * @return 找到的节点
     */
    public TreeNode preOrderQuery(int id){
        if (root == null){
            System.out.println("树根节点为空");
            return null;
        }
        return preOrderQuery(id, root);
    }

    /**
     * 中序查找
     * @param id 需查找节点的id
     * @return 找到的节点
     */
    public TreeNode infixOrderQuery(int id){
        if (root == null) {
            System.out.println("树根节点为空");
            return null;
        }
        return infixOrderQuery(id, root);
    }

    /**
     * 后序查找
     * @param id 需查找节点的id
     * @return 找到的节点
     */
    public TreeNode postOrderQuery(int id){
        if (root == null) {
            System.out.println("树根节点为空");
            return null;
        }
        return postOrderQuery(id, root);
    }

    /**
     * 删除节点
     * @param id 需删除节点的id
     * @return 是否删除
     */
    public boolean delete(int id){
        if (root == null) {
            System.out.println("树根节点为空");
            return false;
        }
        if (id == root.id) {
//            System.out.println("已删除节点" + root);
            root = null;
            return true;
        }
        return delete(id, root);
    }
}
