package com.jinyu.io;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
//        list.foreach(system.out::println);

//        System.out.println(list.iterator().next());
//        for (Iterator<Integer> iterator = list.iterator();iterator.hasNext();){
//            System.out.println(iterator.next());
//        }
        for (Integer i : list){
            System.out.println(i);
        }
    }
}
