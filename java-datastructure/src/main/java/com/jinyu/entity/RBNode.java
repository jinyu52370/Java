package com.jinyu.entity;


import static com.jinyu.entity.RBTreeColor.BLACK;
import static com.jinyu.entity.RBTreeColor.RED;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/26 19:14
 */
public class RBNode{
    public int id;
    public Object data;
    public RBTreeColor color;

    public RBNode left;
    public RBNode right;

    public RBNode(int id, RBTreeColor color, Object data){
        this.id = id;
        this.data = data;
        this.color = color;
    }

    public void swapColor(){
        color = color == RED ? BLACK : RED;
    }

    @Override
    public String toString() {
        return "RBNode{" +
                "id=" + id +
                ", data=" + data +
                ", color=" + color +
                '}';
    }
}
