package com.jinyu.io;

import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/2/22 21:41
 */
public class IOOtherTest {
    //region LineNumberReader
    /**
     * LineNumberReader
     */
    @Test
    public void test() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("F:\\fileIndex\\a.txt"));
//
//        String line;
//        while ((line = br.readLine()) != null){
//            System.out.println(line);
//        }
//
//        br.close();

        LineNumberReader lnr = new LineNumberReader(new FileReader("F:\\fileIndex\\a.txt"));

        String line;
        while ((line = lnr.readLine()) != null){
            System.out.println(lnr.getLineNumber() + "\t" + line);
        }

        lnr.close();
    }
    //endregion

    //region 数据输入输出流
    /**
     * 数据输出流
     *  DataOutputStream
     */
    @Test
    public void test1() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("F:\\fileIndex\\data.txt"));

        dos.writeByte(97);
        dos.writeUTF("zxc");
        dos.writeBoolean(false);

        dos.close();
    }

    /**
     * 数据输入流
     *  DataInputStream
     */
    @Test
    public void test2() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("F:\\fileIndex\\data.txt"));
        System.out.println(dis.read());
        System.out.println(dis.readUTF());
        System.out.println(dis.readBoolean());

        dis.close();
    }
    //endregion

    //region 内存操作流
    /**
     * 操作字节数组
     */
    @Test
    public void test3() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("hhh".getBytes());

        //可以不用关
        baos.close();

        byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes));

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        int ch;
        while ((ch = bais.read()) != -1){
            System.out.print(((char) ch));
        }
    }

    /**
     * 操作字符数组
     */
    @Test
    public void test4() throws IOException {
        CharArrayWriter caw = new CharArrayWriter();
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        caw.write(chars);
        caw.close();

        CharArrayReader car = new CharArrayReader(chars);
        int ch;
        while ((ch = car.read()) != -1){
            System.out.print(((char) ch));
        }
    }

    /**
     * 操作字符串
     */
    @Test
    public void test5() throws IOException {
        StringWriter sw = new StringWriter();
        sw.write("stringWriter");
        sw.close();

        StringReader sr = new StringReader(sw.toString());
        int ch;
        while ((ch = sr.read()) != -1){
            System.out.print(((char) ch));
        }
    }
    //endregion

    //region 打印流
    /*
     * 打印流特殊功能：
     *  1.可以打印所有的数据：
     *      基本类型
     *      引用类型：该类型对象的toString()方法
     *  2.可以自动刷新
     *
     */
    /**
     * 字节打印流
     */
    @Test
    public void test6() throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream("a.txt"),true);
        ps.println(123);
    }

    /**
     * 字符打印流
     */
    @Test
    public void test7() throws IOException {
        //字符 -- 自动刷新构造方法
        PrintWriter pwfw = new PrintWriter(new FileWriter("F:\\fileIndex\\a.txt"),true);
        //字节 -- 自动刷新构造方法
        PrintWriter pwfos = new PrintWriter(new FileOutputStream("F:\\fileIndex\\a.txt"),true);
//        pw.write("PrintWriter");

        pwfw.println(true);
        pwfw.println(1);
        pwfw.println(1.1f);
        pwfw.println(12.3);
        pwfw.println("123");
        pwfw.println(new Object());
        pwfw.println('c');

//        pw.flush();
//        pw.close();
    }
    //endregion

    //region 标准输入输出流
    /**
     * 标准输入输出流
     */
    @Test
    public void test8() throws IOException {
        //创建输入输出流对象
        BufferedReader br = new BufferedReader(new FileReader("src\\com\\jjj\\IOOtherTest.java"));
        OutputStream os = System.out;

        String line;//用于存储读取到的数据
        while ((line = br.readLine()) != null) {
            os.write((line + "\n").getBytes());
        }
        //释放资源
        os.close();
        br.close();
    }
    //endregion

    //region 合并流
    /**
     * 合并流
     */
    @Test
    public void test9() throws IOException {
        //参数在前的先被打印
        SequenceInputStream si = new SequenceInputStream(new FileInputStream("src\\com\\jjj\\IteratorTest.java"), new FileInputStream("a.txt"));

        OutputStream os = new FileOutputStream("sequence.txt");
        int len;
        byte[] b = new byte[1024];
        while ((len = si.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.close();
        si.close();
    }
    //endregion

    /**
     * 随机访问文件流
     */
    @Test
    public void test10(){
        File file = new File("F:" + File.separator + "fileIndex" + File.separator + "a.txt");

    }

    //region properties
    /**
     * properties -- 读取file.properties属性列表，生成属性文件a.properties
     */
    @Test
    public void propertiesTest() throws IOException {
        Properties prop = new Properties();
        //读取属性文件file.properties
        InputStream in = new BufferedInputStream(new FileInputStream("file.properties"));
        ///加载属性列表
        prop.load(in);

//        for (String key : prop.stringPropertyNames()) {
//            System.out.println(key + ":" + prop.getProperty(key));
//        }
        prop.list(System.out);
        in.close();

        ///保存属性到a.properties文件
        FileOutputStream oFile = new FileOutputStream("a.properties");
        prop.setProperty("phone", "10086");
        prop.store(oFile, "The New properties file");
        oFile.close();
    }
    //endregion

    //region serializable
    /**
     * serializable
     */
    @Test
    public void serializableTest() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt"));
        oos.writeObject(Arrays.asList("jjj",20,"M"));
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"));
        System.out.println(ois.readObject());
        ois.close();
    }
    //endregion
}
