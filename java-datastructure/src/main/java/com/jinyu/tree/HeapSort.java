package com.jinyu.tree;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/17 10:46
 */
public class HeapSort {
    private static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //若此非叶节点的左节点小于右节点
            if (j + 1 < length && array[j] < array[j + 1]) {
                //索引右节点
                j++;
            }
            //若子节点大于父节点，则将子节点赋值给父节点
            if (array[j] > temp) {
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

    private void swap(int[] tree, int i, int j) {
        int temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    /**
     * 堆化
     */
    private void heapify(int[] tree, int len, int i) {
        //左子节点
        int cl = 2 * i + 1;
        //右子节点
        int cr = 2 * i + 2;
        int max = i;

        //找到最大的值
        if (cl < len && tree[cl] > tree[max]) {
            max = cl;
        }
        if (cr < len && tree[cr] > tree[max]) {
            max = cr;
        }
        //若max发生过变化
        if (max != i) {
            //将最大节点max换到parent节点
            swap(tree, i, max);
            //将交换后的节点递归堆化
            heapify(tree, len, max);
        }
    }

    /**
     * 由下而上构建堆
     */
    private void buildHeap(int[] tree, int len) {
        int lastIndex = len - 1, parent = (lastIndex - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            heapify(tree, len, i);
        }
    }

    /**
     * 堆排序
     */
    public int[] heapSort(int[] tree) {
        int len = tree.length;
        buildHeap(tree, len);

        for (int i = len - 1; i >= 0; i--) {
            //将最后一个节点和root节点交换
            swap(tree, i, 0);
            //切断交换后的最后一个节点，即最大值，再次堆化
            heapify(tree, i, 0);
        }
        return tree;
    }
}
