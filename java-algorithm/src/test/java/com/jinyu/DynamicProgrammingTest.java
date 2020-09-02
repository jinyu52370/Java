package com.jinyu;

import org.junit.Test;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/23 17:07
 */
public class DynamicProgrammingTest {
    @Test
    public void knapsackProblemTest() {
        DynamicProgramming.knapsackProblem();
    }

    @Test
    public void maxNumTest() {
//        System.out.println(DynamicProgramming.maxNum(new int[]{1, 2, 4, 1, 7, 8, 3}, 6));
        System.out.println(DynamicProgramming.dpMaxNum(new int[]{1, 2, 4, 1, 7, 8, 3}, 6));
    }

    @Test
    public void isHashSumTest() {
        System.out.println(DynamicProgramming.isHashSum(new int[]{3, 34, 4, 12, 5, 2}, 9));
    }
}
