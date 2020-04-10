package com.jinyu;


import java.util.Arrays;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/9 14:15
 */
public class Recursion {
    /**
     * map规定
     *  0：未走过
     *  1：墙
     *  2：路线
     *  3：已走过，但不通
     * 行动策略：下 -> 右 -> 上 -> 左
     *
     * @param map 地图
     * @param startI 起点x坐标
     * @param startJ 起点y坐标
     * @param endI 终点y坐标
     * @param endJ 终点y坐标
     * @return 走完的地图
     */
    public static boolean maze(int[][] map, int startI, int startJ, int endI, int endJ){
        //找到通路
        if (map[endI][endJ] == 2){
            System.out.println("找到通路");
            for (int[] x : map) {
                System.out.print("[");
                for (int i = 0; i < x.length; i++) {
                    System.out.print(x[i]);
                    if (i != map[0].length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
            return true;
        }
        //当前点还未走过
        if (map[startI][startJ] == 0){
            //按照策略 下 -> 右 -> 上 -> 左 走
            //假定该点可以走
            map[startI][startJ] = 2;
            if (maze(map, startI + 1, startJ, endI, endJ)){
                return true;
            }
            if (maze(map, startI, startJ + 1, endI, endJ)){
                return true;
            }
            if (maze(map, startI - 1, startJ, endI, endJ)){
                return true;
            }
            if (maze(map, startI, startJ - 1, endI, endJ)){
                return true;
            }
            map[startI][startJ] = 3;
            return false;
        }
        return false;
    }

    private static int queenNumMax = 8;
    private static int[] result = new int[queenNumMax];
    private static int totalResult = 0;
    private static int totalIsClash = 0;

    /**
     * 判断冲突
     * @param n 第几个皇后
     * @return 是否有冲突
     */
    private static boolean isClash(int n){
        totalIsClash++;
        for (int i = 0; i < n; i++) {
            /*
             * result[i] == result[n]
             *      判断是否在一列上
             * Math.abs(n - i) == Math.abs(result[n] - result[i])
             *      判断是否在对角线上： 斜率为1时，x1 - x2 = y1 - y2
             */
            if (result[i] == result[n] || Math.abs(n - i) == Math.abs(result[n] - result[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 放皇后
     */
    private static void putQueen(int n){
        //放置第9个皇后时即8个皇后均放置完毕
        if (n == queenNumMax){
            totalResult++;
            System.out.print(totalResult + "\t[");
            for (int element : result){
                System.out.print(element);
                if (element != result[result.length - 1]){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            return;
        }

        for (int i = 0; i < queenNumMax; i++) {
            //每次从第一列开始放
            result[n] = i;
            //若不冲突则开始放下一行
            if (isClash(n)){
                putQueen(n + 1);
            }
            //若冲突则回到 result[n] = i; 即放第二列并检测冲突，以此类推
        }
    }

    public static void queen8(int n){
        putQueen(n);
        System.out.println("共进行冲突检测" + totalIsClash + "次");
    }
}
