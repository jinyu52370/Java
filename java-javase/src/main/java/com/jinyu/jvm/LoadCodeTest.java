package com.jinyu.jvm;

class Code{
    static {
        System.out.println("Code的 静态 代码块");
    }

    {
        System.out.println("Code的 构造 代码块");
    }

    public Code(){
        System.out.println("Code的 构造 方法");
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/1 9:47
 *
 * 静态块先行，构造块随后，构造方法最后
 *
 * LoadCodeTest的 静态 代码块
 * =============分割线①=============
 * Code的 静态 代码块
 * Code的 构造 代码块
 * Code的 构造 方法
 * =============分割线②=============
 * Code的 构造 代码块
 * Code的 构造 方法
 * =============分割线③=============
 * LoadCodeTest的 构造 代码块
 * LoadCodeTest的 构造 方法
 */
public class LoadCodeTest {
    static {
        System.out.println("LoadCodeTest的 静态 代码块");
    }

    {
        System.out.println("LoadCodeTest的 构造 代码块");
    }

    public LoadCodeTest(){
        System.out.println("LoadCodeTest的 构造 方法");
    }

    public static void main(String[] args) {
        System.out.println("=============分割线①=============");
        new Code();
        System.out.println("=============分割线②=============");
        new Code();
        System.out.println("=============分割线③=============");
        new LoadCodeTest();
    }
}
