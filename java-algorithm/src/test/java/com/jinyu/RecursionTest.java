package com.jinyu;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/9 15:19
 */
public class RecursionTest {
    @Test
    public void test1(){
        /*
         * map规定
         *  0：未走过
         *  1：墙
         *  2：路线
         *  3：已走过，但不通
         * 行动策略：下 -> 右 -> 上 -> 左
         */
        int[][] map = new int[][]{
                {1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,1,1,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,1,1,1,1,1,1}};
        //起点坐标
        int startI = 1, startJ = 1;
        //终点坐标
        int endI = 6, endJ = 5;
        Recursion.maze(map,startI , startJ, endI, endJ);
    }

    @Test
    public void test2(){
        Recursion.queen8(0);
    }
}
