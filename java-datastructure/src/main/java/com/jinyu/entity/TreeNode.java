package com.jinyu.entity;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/14 16:09
 */
public class TreeNode {
    public int id;
    public Object[] objects;
    public TreeNode left = null;
    public TreeNode right = null;

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }


    public TreeNode(int id, Object... objects){
        this.id = id;
        this.objects = objects;
    }
}
