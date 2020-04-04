package com.jinyu;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/3 21:31
 */
public class Sort {
    /**
     * 直接插入排序
     *
     *  通过交换进行插入排序，借鉴冒泡排序
     */
    public static void directInsertionSort1(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 直接插入排序
     *
     *  通过将较大的元素都向右移动而不总是交换两个元素
     */
    public static void directInsertionSort2(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int num = a[i];
            int j;
            for (j = i; j > 0 && num < a[j - 1]; j--) {
                a[j] = a[j - 1];
            }
            a[j] = num;
        }
    }

    /*
     *
     */

    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
