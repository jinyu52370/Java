package com.jinyu.io;

import org.junit.Test;

import java.io.*;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 *  流向：(无论输入流或输出流均相对于当前程序)
 *      输入流(read) -- input
 *      输出流(write) -- output
 *  传输的内容：
 *      字节数据：图片、音频、视频
 *          字节输入流：InputStream
 *          字节输出流：OutputStream
 *      字符数据：abcdef123
 *          字符输入流：Reader
 *          字符输出流：Writer
 *
 *  无论字节流还是字符流，底层流动的均是字节！(字符流是对字节流的包装)
 * @date 2020/2/20 19:50
 */
public class IOTest {
    /**
     * 练习：通过IO流往文件中写入“Hello IO”
     *  字节输出流
     */
    @Test
    public void test() throws IOException {
        String data = "Hello IO";

        FileOutputStream fos = new FileOutputStream("F:\\fileIndex\\a.txt");

        //write(byte b[])
//        fos.write(data.getBytes());

        //write(int b) -- ASCII code
//        fos.write(97);

        //write(byte b[], int off, int len) -- 写入 Hell
        fos.write(data.getBytes(), 0, 4);

        fos.close();
    }

    /**
     * 字节输入流
     */
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("F:\\fileIndex\\a.txt");

        //fis.available() -- 返回文件内容的字节数

        //fis.skip(long n) -- 跳过n个字节开始读取
        int skipNum = 4;
        fis.skip(skipNum);

        System.out.println(((char) fis.read()));

        fis.close();
    }

    /**
     * 字符输出流
     */
    @Test
    public void test2() throws IOException {
        FileWriter fw = new FileWriter("F:\\fileIndex\\a.txt");

        fw.write(97 + "\n");
        fw.write("われわれわ辛辣天塞\n");
        fw.append("jjj\n");

        try {
            fw.write((String) null);
        } catch (NullPointerException e) {
            System.err.println("fileWriter.write(null)时会空指针异常，而append(null)不会");
        }

        fw.append(null);

        fw.close();
    }

    /**
     * 字符输入流
     */
    @Test
    public void test3() throws IOException {
        FileReader fr = new FileReader("F:\\fileIndex\\a.txt");

        fr.skip(1);
        System.out.println(fr.read());

        fr.close();
    }
}
