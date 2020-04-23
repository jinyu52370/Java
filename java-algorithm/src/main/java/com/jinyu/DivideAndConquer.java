package com.jinyu;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/23 12:29
 */
public class DivideAndConquer {
    public static void hanoiTower(int num, char a, char b, char c){
        //只有一个盘
        if (num == 1){
            System.out.println("第1个盘\t" + a + " -> " + b);
        } else {
            /*
             * 若num>=2时，认为只有两个盘：
             *  最底层的盘
             *  底层上的所有盘
             */
            //1.将底层上的所有盘从 a -> b，利用c移动
            hanoiTower(num - 1, a, c, b);
            //2.将底层的盘从 a -> c
            System.out.println("第" + num + "个盘\t" + a + " -> " + c);
            //3.将B塔的所有盘从 b -> c， 利用a移动
            hanoiTower(num - 1, b, a, c);
        }
    }
}
