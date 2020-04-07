package com.jinyu.impl;

import com.jinyu.Queue;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 17:12
 */
public class QueueUseArray implements Queue {
    private int maxSize;
    private int front;
    private int rear;
    private Object[] arr;

    public QueueUseArray(int maxSize){
        this.maxSize = maxSize;
        arr = new Object[maxSize];
        //指向队列的前一个位置
        front = -1;
        //指向队列尾
        rear = -1;
    }

    @Override
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    @Override
    public boolean isEmpty(){
        return rear == front;
    }

    @Override
    public void add(Object o){
        if (isFull()) {
            throw new IndexOutOfBoundsException("队列已满，无法添加数据！");
        }
        arr[++rear] = o;
    }

    @Override
    public Object get(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("队列为空，无法取出数据！");
        }
        return arr[++front];
    }

    @Override
    public int length() {
        return rear + 1;
    }

    @Override
    public void print(){
        if (isEmpty()){
            System.out.println("队列为空，无法打印数据！");
            return;
        }
        System.out.print("[");
        for (Object o : arr){
            System.out.print(o);
            if (o != arr[arr.length - 1]){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    @Override
    public Object peek(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("队首为空，无法查看数据！");
        }
        return arr[front + 1];
    }
}
