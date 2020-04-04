package com.jinyu;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 19:17
 */
public class ArrayCycleQueue {
    private int maxSize;
    private int front;
    private int rear;
    private Object[] arr;

    public ArrayCycleQueue(int maxSize){
        //约定留一个rear指向的空位
        maxSize++;
        this.maxSize = maxSize;
        arr = new Object[maxSize];
        //指向队首
        front = 0;
        //指向队列尾的后一个位置
        rear = 0;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void add(Object o){
        if (isFull()) {
            throw new IndexOutOfBoundsException("队列已满，无法添加数据！");
        }
        arr[rear] = o;
        //rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    public Object get(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("队列为空，无法取出数据！");
        }
        Object temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    public int effectiveNum(){
        return (rear + maxSize - front) % maxSize;
    }

    public void print(){
        if (isEmpty()){
            System.out.println("队列为空，无法打印数据！");
            return;
        }
        System.out.print("[");
        for (int i = front; i < front + effectiveNum(); i++) {
            System.out.print(arr[i % maxSize]);
            if (arr[i % maxSize] != arr[(rear + maxSize - 1) % maxSize]){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public Object peek(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("队首为空，无法查看数据！");
        }
        return arr[front];
    }
}
