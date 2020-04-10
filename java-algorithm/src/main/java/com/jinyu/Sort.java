package com.jinyu;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/3 21:31
 */
public class Sort {
    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            boolean isOrderly = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    isOrderly = false;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            //若在一趟排序中均未发生交换，则表示已经有序
            if (isOrderly){
                break;
            }
        }
        return array;
    }

    /**
     * 选择排序
     */
    public static int[] selectionSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length - i; j++) {
                if (array[minIndex] > array[j]){
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

//    /**
//     * 直接插入排序
//     *
//     *  通过交换进行插入排序，借鉴冒泡排序
//     */
//    public static void insertionSort1(int[] a) {
//        for (int i = 0; i < a.length - 1; i++) {
//            for (int j = i + 1; j > 0; j--) {
//                if (a[j - 1] > a[j]) {
//                    int temp = a[j - 1];
//                    a[j - 1] = a[j];
//                    a[j] = temp;
//                }
//            }
//        }
//    }

    /**
     * 插入排序
     *  i：无序表首元素下标
     *  j：有序表末元素下标，在无序表首元素的前一个
     *  value：记录无序表首元素
     *  array[j]：有序表末元素
     *
     *  若无序表首元素小于有序表末元素则有序表末元素后移（占据无序表首元素位置，已记录在value不会丢失），
     *  有序表末元素下标前移，直到待插入元素小于无序表首元素，则无序表首元素插入j的后面
     */
    public static int[] insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j;
            for (j = i - 1; j >= 0 && value < array[j]; j--){
                array[j + 1] = array[j];
            }
            /*
             * 找到插入位置，在待插入元素的后一个
             *
             * 若value > array[i]，即无序表首元素大于有序表末元素，即j + 1 == i则无需插入
             */
            if (j + 1 != i) {
                array[j + 1] = value;
            }
        }
        return array;
    }

    /**
     * 希尔排序
     */
    public static int[] shellSort(int[] array) {
        int temp;
        for (int stepLength = array.length / 2; stepLength > 0; stepLength /= 2) {
            for (int i = stepLength; i < array.length; i++) {
                //遍历各组中元素
                for (int j = i - stepLength; j >= 0; j -= stepLength) {
                    //前一个元素大于加上步长的元素则交换
                    if (array[j] > array[j + stepLength]) {
                        temp = array[j];
                        array[j] = array[j + stepLength];
                        array[j + stepLength] = temp;
                    }
                }
            }
        }
        return array;
    }
}
