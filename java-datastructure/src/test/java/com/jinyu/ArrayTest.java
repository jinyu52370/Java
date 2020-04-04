package com.jinyu;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 16:45
 */
public class ArrayTest {
    private int[][] arr = { {0, 0, 0, 22, 0, 0, 15},
                            {0, 11, 0, 0, 0, 17, 0},
                            {0, 0, 0, -6, 0, 0, 0},
                            {0, 0, 0, 0, 0, 39, 0},
                            {91, 0, 0, 0, 0, 0, 0},
                            {0, 0, 28, 0, 0, 0, 0}};
    private String path = "F:/_IOFiles/sparseArray/array1.txt";

    @Test
    public void twoDArray2SparseArray() {
        int[][] sparseArray = Array.twoDArray2SparseArray(arr);
        for (int[] arrRow : sparseArray) {
            for (int value : arrRow) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void sparseArray2TwoDArray() {
        //sparseArray2TwoDArray
        int[][] arr1 = Array.sparseArray2TwoDArray(Array.twoDArray2SparseArray(arr));
        for (int[] arrRow : arr1) {
            for (int value : arrRow) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void writeSparseArray() {
        int[][] sparseArray = Array.twoDArray2SparseArray(arr);
        Array.writeSparseArray(sparseArray, path);
        System.out.println("写入成功");
    }

    @Test
    public void readSparseArray() {
        int[][] sparseArray1 = Array.readSparseArray(path);
        for (int[] arrRow : sparseArray1) {
            for (int value : arrRow) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
