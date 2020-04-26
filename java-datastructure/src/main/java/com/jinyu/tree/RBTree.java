package com.jinyu.tree;

import com.jinyu.entity.RBNode;
import static com.jinyu.entity.RBTreeColor.BLACK;
import static com.jinyu.entity.RBTreeColor.RED;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/26 19:13
 */
public class RBTree {
    //region private & constructor
    /**
     * 根节点
     */
    private RBNode root;

    public RBTree(int id, Object data){
        this.root = new RBNode(id, BLACK, data);
        addNil(root);
    }

    public RBTree(){
    }
    //endregion

    private void addNil(RBNode node){
        node.left = new RBNode(-1, BLACK, null);
        node.right = new RBNode(-1, BLACK, null);
    }

    public boolean add(int id, Object data){
        if (root == null){
            root = new RBNode(id, BLACK, data);
            addNil(root);
            return true;
        }
        return add(new RBNode(id, RED, data), root);
    }
    private boolean add(RBNode node, RBNode root){
        if (node.id <= root.id) {
            if (root.left.id == -1) {
                root.left = node;
                addNil(root.left);
                return true;
            }
            add(node, root.left);
        }
        if (node.id > root.id) {
            if (root.right.id == -1) {
                root.right = node;
                addNil(root.right);
                return true;
            }
            add(node, root.right);
        }
        //todo 旋转 变色
        return false;
    }

    public boolean remove(int id){
        return false;
    }

    public boolean update(int id, Object data){
        if (root == null){
            System.out.println("红黑树为空");
            return false;
        }
        return update(id, data, root);
    }
    private boolean update(int id, Object data, RBNode node){
        if (node.id == id){
            node.data = data;
            return true;
        }
        if (node.id < id && node.left.id != -1){
            return update(id, data, node.left);
        }
        if (node.id > id && node.right.id != -1){
            return update(id, data, node.right);
        }
        return false;
    }

    public Object query(int id){
        if (root == null){
            System.out.println("红黑树为空");
            return null;
        }
        return query(id, root);
    }
    private Object query(int id, RBNode node){
        if (node.id == id){
            return node.data;
        }
        if (node.id < id && node.left.id != -1){
            return query(id, node.left);
        }
        if (node.id > id && node.right.id != -1){
            return query(id, node.right);
        }
        return null;
    }

    public void preOrder(){
        if (root == null){
            System.out.println("红黑树为空");
            return;
        }
        preOrder(root);
    }
    private void preOrder(RBNode node){
        System.out.println(node);
        if (node.left != null){
            preOrder(node.left);
        }
        if (node.right != null){
            preOrder(node.right);
        }
    }

    public void infixOrder(){

    }

    public void postOrder(){

    }
}
