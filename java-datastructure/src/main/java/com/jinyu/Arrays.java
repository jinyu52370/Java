package com.jinyu;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/4 12:00
 */
public class Arrays {
    /**
     * 二维数组转稀硫数组
     *  1. 遍历原始的二维数组，得到有效数据的个数sum
     *  2. 根据sum就可以创建稀疏数组，sparseArr = int[sum+1][3]
     *  3. 将二维数组的有效数据数据存入到稀疏数组
     *
     * 稀疏数组转二维数组
     *  1. 根据第一行数组创建二维数组
     *          int i = sparseArr[0][0];
     *          int j = sparseArr[0][1];
     *          arr = int[i][j];
     *  2. 读取稀疏数组1~N行的数据，赋值给二维数组
     */
    public static int[][] twoDArray2SparseArray(int[][] array){
        int valueNum = 0;


        //遍历二维数组，获取非0值的个数
        for (int[] arrRow : array){
            for (int arrValue : arrRow){
                if (arrValue != 0){
                    valueNum ++;
                }
            }
        }

        //创建对应的稀疏数组
        int[][] sparseArr = new int[valueNum + 1][3];
        sparseArr[0][0] = array.length;
        sparseArr[0][1] = array[0].length;
        sparseArr[0][2] = valueNum;

        //计数器，记录第几个非0值
        int count = 0;
        //遍历二维数组，将非0值及其坐标存入稀疏数组
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     *
     */
    public static int[][] sparseArray2TwoDArray(int[][] sparseArray){
        int[][] arr = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int i = 1; i <= sparseArray[0][2]; i++) {
            arr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return arr;
    }

    /**
     *
     */
    public static void writeSparseArray(int[][] sparseArray, String path){
        try {
            FileWriter fw = new FileWriter(path);

            for (int[] arrRow : sparseArray) {
                for (int value : arrRow){
                    fw.write(String.valueOf(value + "\t"));
                }
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] readSparseArray(String path){
        String line;
        int rowNum = 0, rowIndex = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            rowNum = (int)Files.lines(Paths.get(path)).count();

            int[][] sparseArray = new int[rowNum][3];
            while ((line = br.readLine()) != null){
                String[] rowArr = line.split("\t");
                for(int i = 0;i < rowArr.length; i++){
                    sparseArray[rowIndex][i] = Integer.parseInt(rowArr[i]);
                }
                rowIndex++;
            }
            br.close();
            return sparseArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
