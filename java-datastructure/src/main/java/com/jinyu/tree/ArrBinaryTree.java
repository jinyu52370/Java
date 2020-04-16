package com.jinyu.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/16 9:53
 *
 * 顺序存储二叉树
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrBinaryTree {
    private int[] arr;

    private void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        System.out.print(arr[index] + "\t");
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void preOrder(){
        this.preOrder(0);
    }
}
