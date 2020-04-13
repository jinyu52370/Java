package com.jinyu.hash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/13 19:35
 */
@Data
@NoArgsConstructor
public class UserLinkedList {
    private User head;

    public void add(User user){
        if (head == null){
            head = user;
            return;
        }
        User temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = user;
    }

    public void queryAll(){
        if (head == null){
            System.out.println("链表为空");
            return;
        }
        User temp = head;
        while (temp != null){
            System.out.print(temp + "\t=>\t");
            temp = temp.next;
        }
        System.out.println();
    }

    public User queryById(int id){
        if (head == null){
            return null;
        }
        User temp = head;
        while (temp != null){
            if (temp.getId() == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}
