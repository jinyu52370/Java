package com.jinyu.tree;

import com.jinyu.entity.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/20 11:27
 */
public class BinarySortTree extends BinaryTree {
    //region private
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
    }

    private TreeNode query(int id, TreeNode node) {
        if (id == node.id) {
            return node;
        }
        if (id < node.id && node.left != null) {
            return query(id, node.left);
        }
        if (id > node.id && node.right != null) {
            return query(id, node.right);
        }
        return null;
    }

    private TreeNode queryParent(int id, TreeNode node) {
        if (node.left != null && id == node.left.id) {
            return node;
        }
        if (node.right != null && id == node.right.id) {
            return node;
        }
        if (id < node.id && node.left != null) {
            return queryParent(id, node.left);
        }
        if (id > node.id && node.right != null) {
            return queryParent(id, node.right);
        }
        return null;
    }
    //endregion

    //region constructor
    public BinarySortTree() {
    }

    public BinarySortTree(int[] array) {
        create(array);
    }
    //endregion

    public void create(int[] array) {
        root = new TreeNode(array[0]);
        for (int i = 1; i < array.length; i++) {
            create(new TreeNode(array[i]), root);
        }

    }

    public void add(int[] array) {
        for (int a : array) {
            create(new TreeNode(a), root);
        }
    }

    public void add(int value) {
        create(new TreeNode(value), root);
    }

    public TreeNode query(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return query(id, root);
    }

    public TreeNode queryParent(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return queryParent(id, root);
    }

    //寻找待删除节点右子树的最小节点(即向左遍历直到叶子结点)
    private int queryTargetRightMinId(TreeNode target){
        target = target.right;
        while (target.left != null){
            target = target.left;
        }
        int temp = target.id;
        delete(target.id);
        return temp;
    }

    /**
     * 三种情况
     * 1. 叶子结点
     * 2. 只有一颗子树的结点
     * 3. 有两颗子树的结点
     */
    @Override
    public boolean delete(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
            return false;
        }
        TreeNode target = query(id);
        if (target == null) {
            System.out.println("值为" + id + "的结点不存在");
            return false;
        }

        TreeNode parent = queryParent(id);

        //1. 叶子结点
        if (target.left == null && target.right == null) {
            //该节点为root且没有左右子树
            if (parent == null) {
                root = null;
                return true;
            }
            //待删除节点为其父节点的左子节点
            if (parent.left != null && parent.left.id == target.id) {
                parent.left = null;
                return true;
            }
            //待删除节点为其父节点的右子节点
            if (parent.right != null && parent.right.id == target.id) {
                parent.right = null;
                return true;
            }
        }
        //2. 有两颗子树的结点
        if (target.left != null && target.right != null) {
            target.id = queryTargetRightMinId(target);
            return true;
        }
        //3. 只有一颗子树的结点
        //待删除节点只有左子树
        if (target.left != null) {
            //待删除节点为root
            if (parent == null){
                root = root.left;
                return true;
            }
            //待删除节点为其父节点的左子节点
            if (parent.left != null && parent.left.id == target.id) {
                parent.left = target.left;
                return true;
            }
            //待删除节点为其父节点的右子节点
            parent.right = target.left;
            return true;
        }
        //待删除节点只有右子树
        if (target.right != null) {
            //待删除节点为root
            if (parent == null){
                root = root.right;
                return true;
            }
            //待删除节点为其父节点的左子节点
            if (parent.left != null && parent.left.id == target.id) {
                parent.left = target.right;
                return true;
            }
            //待删除节点为其父节点的右子节点
            parent.right = target.right;
            return true;
        }

        return false;
    }
}
