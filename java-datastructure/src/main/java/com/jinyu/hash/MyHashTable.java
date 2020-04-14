package com.jinyu.hash;


import com.jinyu.entity.User;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/13 19:28
 */
public class MyHashTable {
    private UserLinkedList[] userLinkedListArray;
    private int size;

    public MyHashTable(int size){
        this.size = size;
        userLinkedListArray = new UserLinkedList[size];
        for (int i = 0; i < userLinkedListArray.length; i++) {
            userLinkedListArray[i] = new UserLinkedList();
        }
    }

    public void add(User user){
        int arrayIndex = hashFunction(user.getId());
        userLinkedListArray[arrayIndex].add(user);
    }

    private int hashFunction(int id){
        return (id - 1) % size;
    }

    public void queryAll() {
        for (int i = 0; i < userLinkedListArray.length; i++) {
            System.out.print(i + "\t");
            userLinkedListArray[i].queryAll();
        }
    }

    public User queryById(int id){
        if (userLinkedListArray == null){
            return null;
        }
        return userLinkedListArray[hashFunction(id)].queryById(id);
    }
}
