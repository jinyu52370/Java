package com.jinyu;

import com.jinyu.tree.HeapSort;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/3 21:31
 */
public class Sort {
    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] array) {
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
            if (isOrderly) {
                break;
            }
        }
        return array;
    }

    /**
     * 选择排序
     */
    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length - i; j++) {
                if (array[minIndex] > array[j]) {
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
     * i：无序表首元素下标
     * j：有序表末元素下标，在无序表首元素的前一个
     * value：记录无序表首元素
     * array[j]：有序表末元素
     * <p>
     * 若无序表首元素小于有序表末元素则有序表末元素后移（占据无序表首元素位置，已记录在value不会丢失），
     * 有序表末元素下标前移，直到待插入元素小于无序表首元素，则无序表首元素插入j的后面
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int j;
            for (j = i - 1; j >= 0 && value < array[j]; j--) {
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
     * 希尔排序 交换法
     */
    public static int[] shellSortSwap(int[] array) {
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

    /**
     * 希尔排序 移动法
     */
    public static int[] shellSortMove(int[] array) {
        for (int stepLength = array.length / 2; stepLength > 0; stepLength /= 2) {
            for (int i = stepLength; i < array.length; i++) {
                int value = array[i];
                int j;
                for (j = i - stepLength; j >= 0 && value < array[j]; j -= stepLength) {
                    array[j + stepLength] = array[j];
                }
                if (j + stepLength != i) {
                    array[j + stepLength] = value;
                }
            }
        }
        return array;
    }

    /**
     * 快速排序执行方法
     */
    private static void quickSortExecute(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];
        int l = left, r = right;
        while (l < r) {
            //找到大于等于pivot的数的下标
            while (array[l] < pivot) {
                l++;
            }
            //找到小于等于pivot的数的下标
            while (array[r] > pivot) {
                r--;
            }
            //l == r 则表示l和r均等于pivot的下标，左侧 < pivot < 右侧
            if (l == r) {
                break;
            }
            //交换
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            /*
             * 交换完后，若array[l]等于pivot，则r需要左移防止array[l]和pivot无限交换
             *
             * 例：1 2 3 4 2 7 8 9 5
             * 第一轮：
             *  pivot = array[(0 + 8) / 2] = 2
             *  l = 2
             *  r = pivot = 2
             *
             *  若交换后不对r左移，则下一轮循环中l和r都不会移动，然后再次交换，以至无限
             */
            if (array[l] == pivot) {
                r--;
            }
            //同理
            if (array[r] == pivot) {
                l++;
            }
        }
        //若l == r则表示l和r均等于pivot的下标，r左移，l右移跳过pivot分别去对pivot的左侧和右侧递归
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSortExecute(array, left, r);
        }
        //向右递归
        if (right > l) {
            quickSortExecute(array, l, right);
        }
    }

    /**
     * 快速排序
     */
    public static int[] quickSort(int[] array) {
        quickSortExecute(array, 0, array.length - 1);
        return array;
    }

    /**
     * 归并排序-归并
     *
     * @param array     待排数组
     * @param minIndex1 左数组的首下标
     * @param endIndex2 右数组的尾下标
     * @param temp      临时数组
     */
    private static void mergeSortMergeAndJoin(int[] array, int minIndex1, int endIndex2, int[] temp) {
        if (minIndex1 < endIndex2) {
            int endIndex1 = (minIndex1 + endIndex2) / 2;
            //向左递归
            mergeSortMergeAndJoin(array, minIndex1, endIndex1, temp);
            //向右递归
            mergeSortMergeAndJoin(array, endIndex1 + 1, endIndex2, temp);
            //合并且排序
            mergeSortExecute(array, minIndex1, endIndex1, endIndex2, temp);
        }
    }

    /**
     * 归并排序-排序
     *
     * @param array     待排数组
     * @param minIndex1 左数组的首下标
     * @param endIndex1 左数组的尾下标
     * @param endIndex2 右数组的尾下标
     * @param temp      临时数组
     */
    private static void mergeSortExecute(int[] array, int minIndex1, int endIndex1, int endIndex2, int[] temp) {
        int i = minIndex1, j = endIndex1 + 1, tempIndex = 0;
        //1. 两个数组按顺序填充到temp数组，直到两个数组有其中一个填充完毕
        while (i <= endIndex1 && j <= endIndex2) {
            if (array[i] <= array[j]) {
                temp[tempIndex] = array[i];
                tempIndex++;
                i++;
                continue;
            }
            temp[tempIndex] = array[j];
            tempIndex++;
            j++;

        }
        //2. 将有剩余元素的数组按下标递增(因为已有序)填充到temp数组
        while (i <= endIndex1) {
            temp[tempIndex] = array[i];
            tempIndex++;
            i++;
        }
        while (j <= endIndex2) {
            temp[tempIndex] = array[j];
            tempIndex++;
            j++;
        }
        //3. 将temp的元素拷贝到array
        tempIndex = 0;
        int minIndex1Temp = minIndex1;
        while (minIndex1Temp <= endIndex2) {
            array[minIndex1Temp] = temp[tempIndex];
            minIndex1Temp++;
            tempIndex++;
        }

    }

    /**
     * 归并排序
     *
     * @param array 待排数组
     * @return
     */
    public static int[] mergeSort(int[] array) {
        mergeSortMergeAndJoin(array, 0, array.length - 1, new int[array.length]);
        return array;
    }

    /**
     * 基数排序
     */
    public static int[] radixSort(int[] array) {
        //桶的数量
        final int bucketTotal = 10;
        //定义桶
        int[][] bucket = new int[bucketTotal][array.length];
        //定义每个桶存放数据的个数
        int[] bucketElementCounts = new int[bucketTotal];

        //找到array中最大的数
        int max = array[0];
        for(int element : array){
            if (element > max){
                max = element;
            }
        }
        //得到max的位数
        int maxLength = (max + "").length();
        //根据max的位数得到array的每一个元素需要除几次10
        for (int i = 0, power = 1; i < maxLength; i++, power *= 10) {
            //将array中的元素放入桶中
            for (int j = 0; j < array.length; j++) {
                int index = (array[j] / power % 10);
                bucket[index][bucketElementCounts[index]++] = array[j];
            }
            //将桶中的元素放回array中
            int arrayIndex = 0;
            for (int j = 0; j < bucketTotal; j++) {
                int bucketElementIndex = 0;
                while (bucketElementCounts[j] != 0) {
                    array[arrayIndex] = bucket[j][bucketElementIndex];
                    //第i个桶的数据量减1
                    bucketElementCounts[j]--;
                    //第i个桶的索引后移
                    bucketElementIndex++;
                    arrayIndex++;
                }
            }
            //桶元素个数清零
            Arrays.stream(bucketElementCounts).forEach(element -> element = 0);
        }
        return array;
    }


    /**
     * 堆排序
     * in HeapSort class
     */
    @Deprecated
    public static HeapSort heapSort;
}
