package com.jinyu.entity;

import lombok.Data;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/16 10:51
 */
@Data
public abstract class BaseNode {
    public int id;
    public Object[] objects;

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }

    public BaseNode(int id, Object... objects){
        this.id = id;
        this.objects = objects;
    }
}
