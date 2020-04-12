package com.jinyu;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/12 20:21
 */
public class SearchTest {
    @Test
    public void sequenceSearchTest(){
        System.out.println(Search.sequenceSearch(new int[]{1,5,2,8,6,5,2,124,13,2}, 2));
    }

    @Test
    public void binarySearchTest(){
        System.out.println(Search.binarySearch(new int[]{1, 8, 8, 8, 10, 89, 1000, 1234}, 8));
    }

    @Test
    public void interpolationSearch(){
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(Search.interpolationSearch(array, 66));
    }
}
