package com.jinyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/12 18:37
 */
public class Search {
    /**
     * 线性查找
     *
     * @param array 待查数组
     * @param value 待查值
     * @return 待查值的下标集合
     */
    public static List sequenceSearch(int[] array, int value) {
        ArrayList<Integer> resultIndex = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            System.out.println("正在查找");
            if (array[i] == value) {
                resultIndex.add(i);
            }
        }
        if (resultIndex.size() == 0){
            return null;
        }
        return resultIndex;
    }

    /**
     * 二分查找执行
     * @param array 待查数组，数组必须有序
     * @param value 待查值
     * @param left 数组左下标
     * @param right 数组右下标
     * @return 待查值的下标集合
     */
    private static List binarySearchExecute(int[] array, int value, int left, int right) {
        System.out.println("正在查找");
        if (value < array[0] || value > array[array.length - 1] || left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        //待查值大于array[mid]时向右递归
        if (value > array[mid]) {
            return binarySearchExecute(array, value, mid + 1, right);
        }
        //待查值小于array[mid]时向左递归
        if (value < array[mid]) {
            return binarySearchExecute(array, value, left, mid - 1);
        }
        //待查值等于array[mid]时分别向mid的左右进行扫描，以找到等于array[mid]的值的下标
        ArrayList<Integer> resultIndex = new ArrayList<>();
        //向左扫
        for (int i = mid - 1;; i--) {
            if (i < 0 || array[i] != array[mid]){
                break;
            }
            resultIndex.add(i);
        }
        //添加mid
        resultIndex.add(mid);
        //向右扫
        for (int i = mid + 1;; i++) {
            if (i > array.length - 1 || array[i] != array[mid]){
                break;
            }
            resultIndex.add(i);
        }
        return resultIndex;
    }

    /**
     * 二分查找
     *
     * @param array 待查数组，数组必须有序
     * @param value 待查值
     * @return 待查值的下标集合
     */
    public static List binarySearch(int[] array, int value){
        return binarySearchExecute(array, value, 0, array.length - 1);
    }

    /**
     * 插值查找执行
     * @param array 待查数组，数组必须有序
     * @param value 待查值
     * @param left 数组左下标
     * @param right 数组右下标
     * @return 待查值的下标集合
     */
    private static List interpolationSearchExecute(int[] array, int value, int left, int right) {
        System.out.println("正在查找");
        if (value < array[0] || value > array[array.length - 1] || left > right) {
            return null;
        }

        int mid = left + (right - left) * (value - array[left]) / (array[right] - array[left]);
        //待查值大于array[mid]时向右递归
        if (value > array[mid]) {
            return binarySearchExecute(array, value, mid + 1, right);
        }
        //待查值小于array[mid]时向左递归
        if (value < array[mid]) {
            return binarySearchExecute(array, value, left, mid - 1);
        }
        //待查值等于array[mid]时分别向mid的左右进行扫描，以找到等于array[mid]的值的下标
        ArrayList<Integer> resultIndex = new ArrayList<>();
        //向左扫
        for (int i = mid - 1;; i--) {
            if (i < 0 || array[i] != array[mid]){
                break;
            }
            resultIndex.add(i);
        }
        //添加mid
        resultIndex.add(mid);
        //向右扫
        for (int i = mid + 1;; i++) {
            if (i > array.length - 1 || array[i] != array[mid]){
                break;
            }
            resultIndex.add(i);
        }
        return resultIndex;
    }

    /**
     * 差值查找
     *
     * @param array 待查数组，数组必须有序
     * @param value 待查值
     * @return 待查值的下标集合
     */
    public static List interpolationSearch(int[] array, int value) {
        return interpolationSearchExecute(array, value, 0, array.length - 1);
    }

    /**
     * 斐波那契查找
     * @param array 待查数组，数组必须有序
     * @param value 待查值
     * @return 待查值的下标集合
     */
    public static List fibonacciSearch(int[] array, int value){
        if (value < array[0] || value > array[array.length - 1]){
            return null;
        }

        ArrayList<Integer> resultIndex = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;
        int k = 0;

        int[] fib = new int[40];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        //将array扩充到斐波那契的长度
        while (right > fib[k] - 1){
            k++;
        }
        int[] temp = Arrays.copyOf(array, fib[k]);
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = array[right];
        }

        //找value
        int mid;
        while (left <= right){
            mid = left + fib[k - 1] - 1;
            if (value < temp[mid]){
                right = mid - 1;
                //fib[k] = (左)fib[k - 1] + (右)fib[k - 2]
                //fib[k - 1]
                k--;
                continue;
            }
            if (value > temp[mid]){
                left = mid + 1;
                //fib[k - 2]
                k -= 2;
                continue;
            }
            if (value == temp[mid]){
                if (mid <= right) {
                    resultIndex.add(mid);
                } else {
                    resultIndex.add(right);
                }
                return resultIndex;
            }
        }
        return null;
    }
}
