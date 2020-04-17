package com.jinyu.tree;

import com.jinyu.entity.TreeNode;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/17 21:18
 */
@Data
public class HuffmanTree extends BinaryTree{
    public void create(int[] array){
        LinkedList<TreeNode> list = new LinkedList<>();
        for (int i : array){
            list.add(new TreeNode(i));
        }
        list.sort(Comparator.comparingInt(TreeNode::getId));

        TreeNode leftNode, rightNode, parent;
        while (true) {
            leftNode = list.get(0);
            rightNode = list.get(1);

            parent = new TreeNode(leftNode.id + rightNode.id);
            parent.left = leftNode;
            parent.right = rightNode;

            list.remove();
            list.remove();

            if (list.size() == 0){
                break;
            }

            list.add(parent);

            list.sort(Comparator.comparingInt(TreeNode::getId));
        }
        root = parent;
    }

}
