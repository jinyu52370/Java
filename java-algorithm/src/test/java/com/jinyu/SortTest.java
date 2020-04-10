package com.jinyu;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 16:47
 */
public class SortTest {
    @Test
    public void data8WTest(){
        int[] data = new int[80000];
        for (int i = 0; i < 80000; i++) {
            data[i] = (int) (Math.random() * 80000);
        }
        Timestamp t1 = new Timestamp(System.currentTimeMillis());

        //10.88s
//        Sort.bubbleSort(data);
        //1.647s
//        Sort.selectionSort(data);
        //0.612s
//        Sort.insertionSort(data);
        //6.074s
        Sort.shellSort(data);

        System.out.println((new Timestamp(System.currentTimeMillis()).getTime() - t1.getTime())/1000.0 + "s");

    }

    @Test
    public void sortTest() {
//        //int[] => list
//        List list = Arrays.stream(Sort.bubbleSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}))
//                .boxed()
//                .collect(Collectors.toList());
//        System.out.println(Arrays.toString(Sort.bubbleSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1})));
//        System.out.println(Arrays.toString(Sort.selectionSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1})));
//        System.out.println(Arrays.toString(Sort.insertionSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(Sort.shellSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1})));
    }
}
