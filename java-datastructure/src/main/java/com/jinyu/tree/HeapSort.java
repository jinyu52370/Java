package com.jinyu.tree;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/17 10:46
 */
public class HeapSort {
    private static void adjustHeap(int[] array, int i, int length){
        int temp = array[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //若此非叶节点的左节点小于右节点
            if (j + 1 < length && array[j] < array[j + 1]){
                //索引右节点
                j++;
            }
            //若子节点大于父节点，则将子节点赋值给父节点
            if (array[j] > temp){
                array[i] = array[j];
                i = j;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public static int[] sort(int[] array) {
        //将无序序列构建成一个堆(升序：大顶堆；降序：小顶堆)
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        int temp;
        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        for (int i = array.length - 1; i > 0; i--) {
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjustHeap(array, 0, i);
        }
        return array;
    }
}
