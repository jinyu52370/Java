package com.jinyu.entity;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/14 16:09
 */
public class TreeNode extends BaseNode{

    public TreeNode left = null;
    public TreeNode right = null;

    /**
     * 0：左子树
     * 1：前驱节点
     */
    public int leftType = 0;

    /**
     * 0：右子树
     * 1：后继节点
     */
    public int rightType = 0;

    public TreeNode(int id, Object... objects) {
        super(id, objects);
    }
}
