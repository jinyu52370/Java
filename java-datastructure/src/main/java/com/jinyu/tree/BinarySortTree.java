package com.jinyu.tree;

import com.jinyu.entity.TreeNode;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/20 11:27
 */
public class BinarySortTree extends BinaryTree{
    //region constructor
    public BinarySortTree(){
    }

    public BinarySortTree(int[] array){
        create(array);
    }
    //endregion

    private void create(TreeNode node, TreeNode root){
        if (node.getId() <= root.getId()){
            if (root.left == null){
                root.left = node;
                return;
            }
            create(node, root.left);
        }
        if (node.getId() > root.getId()){
            if (root.right == null){
                root.right = node;
                return;
            }
            create(node, root.right);
        }
    }

    public void create(int[] array){
        root = new TreeNode(array[0]);
        for (int i = 1; i < array.length; i++) {
            create(new TreeNode(array[i]), root);
        }

    }

    public void add(int[] array){
        for (int a : array) {
            create(new TreeNode(a), root);
        }
    }

    public void add(int value){
        create(new TreeNode(value), root);
    }

    public TreeNode query(int id){
        if (id == root.id){
            return root;
        }
        if (id < root.id){
            return null;
        }
        if (id > root.id){
            return null;
        }
        return null;
    }

    /**
     * 三种情况
     *  1. 叶子结点
     *  2. 只有一颗子树的结点
     *  3. 有两颗子树的结点
     */
    public void delete(){

    }
}
