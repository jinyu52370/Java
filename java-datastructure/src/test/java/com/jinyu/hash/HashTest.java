package com.jinyu.hash;

import com.jinyu.entity.User;
import com.jinyu.hash.MyHashTable;
import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/13 20:13
 */
public class HashTest {
    @Test
    public void test(){
        MyHashTable hashTable = new MyHashTable(7);
        hashTable.add(new User(1, "jjj1"));
        hashTable.add(new User(2, "jjj2"));
        hashTable.add(new User(3, "jjj3"));
        hashTable.add(new User(4, "jjj4"));
        hashTable.add(new User(5, "jjj5"));
        hashTable.add(new User(6, "jjj6"));
        hashTable.add(new User(7, "jjj7"));
        hashTable.add(new User(8, "jjj8"));
        hashTable.add(new User(9, "jjj9"));
        hashTable.add(new User(10, "jjj10"));
        hashTable.add(new User(11, "jjj11"));
        hashTable.add(new User(12, "jjj12"));
        hashTable.add(new User(13, "jjj13"));
        hashTable.add(new User(14, "jjj14"));

//        hashTable.queryAll();

        System.out.println(hashTable.queryById(12));
    }
}
