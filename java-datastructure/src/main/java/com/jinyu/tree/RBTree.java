package com.jinyu.tree;

import static com.jinyu.tree.RBTree.Color.BLACK;
import static com.jinyu.tree.RBTree.Color.RED;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/26 19:13
 */
public class RBTree<K extends Comparable<K>, V> {
    //region innerClass
    enum Color {
        RED, BLACK
    }

    static class RBNode<K extends Comparable<K>, V>{
        private K key;
        private V value;
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private Color color;

        //region constructor
        RBNode(){}

        RBNode(K key, V value, Color color) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        //endregion

        //region getter & setter
        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
        //endregion

        @Override
        public String toString() {
            return "RBNode{" +
                    "key=" + key +
                    ", value=" + value +
                    ", color=" + color +
                    '}';
        }

        private boolean isRed(){
            return color == RED;
        }

        private boolean isBlack(){
            return color == BLACK;
        }

    }
    //endregion

    //region private & constructor
    /**
     * 根节点
     */
    private RBNode root;

    RBTree(K key, V value){
        this.root = new RBNode(key, value, BLACK);
    }

    RBTree(){
    }
    //endregion

    //region nodeRelation

    private RBNode getParent(RBNode node){
        if (node != null){
            return node.parent;
        }
        return null;
    }
    //endregion

    //region rotate
    private void leftRotate(RBNode node){
        RBNode newNode = node.right;
        node.right = newNode.left;
        if (newNode.left != null){
            newNode.left.parent = node;
        }
        if (node.parent == null){
            root = newNode;
            root.parent = null;
        } else {
            newNode.parent = node.parent;
            if (node == node.parent.left){
                node.parent.left = newNode;
            }
            if (node == node.parent.right){
                node.parent.right = newNode;
            }
        }
        node.parent = newNode;
        newNode.left = node;
    }

    private void rightRotate(RBNode node){
        RBNode newNode = node.left;
        node.left = newNode.right;
        if (newNode.right != null){
            newNode.right.parent = node;
        }
        if (node.parent == null){
            root = newNode;
            root.parent = null;
        } else {
            newNode.parent = node.parent;
            if (node == node.parent.left){
                node.parent.left = newNode;
            }
            if (node == node.parent.right){
                node.parent.right = newNode;
            }
        }
        node.parent = newNode;
        newNode.right = node;
    }
    //endregion

    //region operation
    private void keepBalance(RBNode i) {
        root.color = BLACK;
        /*
         * i：当前节点
         * p：i的父节点
         * u：i的叔叔节点
         * pp：i的祖父节点
         */
        RBNode p = getParent(i);
        RBNode pp = getParent(p);
        RBNode u = null;
        //待插入节点的父节点为红色
        if (p != null && p.color == RED) {
            //若父节点为红色，则一定存在祖父节点
            u = p == pp.left ? pp.right : pp.left;
            //1.叔叔节点存在且为 红色
            if (u != null && u.color == RED) {
                p.color = BLACK;
                u.color = BLACK;
                pp.color = RED;
                keepBalance(pp);
                return;
            }
            //2.叔叔节点不存在或为黑色
            if (u == null || u.color == BLACK) {
                //2.1.插入节点的祖父节点的左子节点为父节点
                if (pp.left == p) {
                    //LL双红
                    if (p.left == i) {
                        p.color = BLACK;
                        pp.color = RED;
                        rightRotate(pp);
                        return;
                    }
                    //LR双红
                    if (p.right == i) {
                        leftRotate(p);
                        keepBalance(p);
                        return;
                    }
                }
                //2.2.插入节点的祖父节点的右子节点为父节点
                if (pp.right == p) {
                    //RR双红
                    if (p.right == i) {
                        p.color = BLACK;
                        pp.color = RED;
                        leftRotate(pp);
                        return;
                    }
                    //RL双红
                    if (p.left == i) {
                        rightRotate(p);
                        keepBalance(p);
                    }
                }
            }
        }
    }

    public boolean add(K key, V value){
        return add(new RBNode(key, value, RED));
    }
    private boolean add(RBNode node){
        /*
         * i：当前节点
         * p：i的父节点
         * u：i的叔叔节点
         * pp：i的祖父节点
         */
        RBNode p = null;
        RBNode i = root;
        //查找插入位置
        while (i != null) {
            p = i;
            /*
             * cmp == 0 -> node.key == root.key
             * cmp < 0 -> node.key < root.key
             * cmp > 0 -> node.key > root.key
             */
            int cmp = node.key.compareTo(i.key);
            if (cmp == 0) {
                root.value = node.value;
                return true;
            }
            if (cmp < 0) {
                i = i.left;
            }
            if (cmp > 0) {
                i = i.right;
            }
        }
        //退出后p为待插入节点的父节点
        node.parent = p;
        //判断node.key和p.key谁大，决定node挂在p的左或右
        if (p != null) {
            if (node.key.compareTo(p.key) < 0) {
                p.left = node;
            }
            if (node.key.compareTo(p.key) > 0) {
                p.right = node;
            }
        }
        //p == null -> root为空
        else {
            root = node;
        }
        keepBalance(node);
        return true;
    }

    public boolean remove(int id){
        return false;
    }

    public V query(K key){
        if (root == null){
            System.out.println("红黑树为空");
            return null;
        }
        return query(key, root);
    }
    private V query(K key, RBNode root){
        int cmp = key.compareTo((K) root.key);
        if (root.key == key){
            return (V) root.value;
        }
        if (cmp < 0 && root.left != null){
            return query(key, root.left);
        }
        if (cmp > 0 && root.right != null){
            return query(key, root.right);
        }
        return null;
    }
    //endregion

    //region order
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
        if (root == null){
            System.out.println("红黑树为空");
            return;
        }
        infixOrder(root);
    }
    private void infixOrder(RBNode node){
        if (node.left != null){
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null){
            infixOrder(node.right);
        }
    }

    public void postOrder(){
        if (root == null){
            System.out.println("红黑树为空");
            return;
        }
        postOrder(root);
    }
    private void postOrder(RBNode node){
        if (node.left != null){
            postOrder(node.left);
        }
        if (node.right != null){
            postOrder(node.right);
        }
        System.out.println(node);
    }
    //endregion
}
