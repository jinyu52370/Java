package com.jinyu;

import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/23 14:58
 */
public class DynamicProgramming {
    public static void knapsackProblem() {
        //物品重量
        int[] weight = {1, 4, 3};
        //物品价值
        int[] value = {1500, 3000, 2000};
        //背包容量
        int capacity = 4;
        //物品种数
        int goodsNum = value.length;
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] table = new int[goodsNum + 1][capacity + 1];
        //记录最优解
        int[][] path = new int[goodsNum + 1][capacity + 1];

        //表的第0列为0
        for (int i = 0; i < goodsNum + 1; i++) {
            table[i][0] = 0;
        }
        //表的第0行为0
        for (int i = 0; i < capacity + 1; i++) {
            table[0][i] = 0;
        }

        //第一行和第一列不处理
        for (int i = 1; i < goodsNum + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                //新加入商品的容量大于当前背包容量时，使用上一个单元格的装入策略
                if (weight[i - 1] > j) {
                    table[i][j] = table[i - 1][j];
                }
                /*
                 * 新加入商品的容量小于等于当前背包容量时，
                 * 装入策略：1.和2.的最大值
                 *  1.上一个单元格的装入策略
                 *  2.当前商品的价值 + 剩余空间的最大价值(在表的过往记录中)
                 */
                else {
                    if (table[i - 1][j] < value[i - 1] + table[i - 1][j - weight[i - 1]]){
                        table[i][j] = value[i - 1] + table[i - 1][j - weight[i - 1]];
                        //记录最优解
                        path[i][j] = 1;
                    } else {
                        table[i][j] = table[i - 1][j];
                    }

                }
            }
        }

        for (int[] t : table) {
            System.out.println(Arrays.toString(t));
        }

        System.out.println();
        int i = path.length - 1;
        int j = path[i].length - 1;
        while (i > 0 && j > 0){
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入到背包");
                j -= weight[i - 1];
            }
            i--;
        }

    }
}
