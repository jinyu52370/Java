package com.jinyu;

import com.jinyu.tree.HeapSort;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;


/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 16:47
 */
public class SortTest {
    @Test
    public void data8WTest() {
        int max = 8 * 1_000_000;
        int[] data = new int[max];
        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * max);
        }
        Timestamp t1 = new Timestamp(System.currentTimeMillis());

        //11.032s
//        Sort.bubbleSort(data);
        //1.713s
//        Sort.selectionSort(data);
        //0.622s
//        Sort.insertionSort(data);
        //6.923s
//        Sort.shellSortSwap(data);
        //0.022s
//        Sort.shellSortMove(data);
        //0.02s
//        Sort.quickSort(data);
        //0.019s
//        Sort.mergeSort(data);
        //0.079s
//        Sort.radixSort(data);
        //0.02s
//        HeapSort.sort(data);
        Sort.mergeSort1(data);

        System.out.println((new Timestamp(System.currentTimeMillis()).getTime() - t1.getTime()) / 1000.0 + "s");

    }

    @Test
    public void sortTest() {
//        //int[] => list
//        List list = Arrays.stream(Sort.bubbleSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}))
//                .boxed()
//                .collect(Collectors.toList());
        int[] array = Sort.getRandomArray(15, 7);
//        System.out.println(Arrays.toString(Sort.bubbleSort(array)));
//        System.out.println(Arrays.toString(Sort.selectionSort(array)));
//        System.out.println(Arrays.toString(Sort.insertionSort(array)));
//        System.out.println(Arrays.toString(Sort.shellSortSwap(array)));
//        System.out.println(Arrays.toString(Sort.shellSortMove(array)));
//        System.out.println(Arrays.toString(Sort.quickSort(array)));
//        System.out.println(Arrays.toString(Sort.quickSort(array)));
//        System.out.println(Arrays.toString(Sort.mergeSort1(array)));
//        System.out.println(Arrays.toString(Sort.radixSort(new int[]{53, 3, 542, 748, 14, 214})));
        System.out.println(Arrays.toString(new HeapSort().heapSort(array)));
    }
}
