package com.jinyu.io;

import com.jinyu.entity.ReflectUser;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/26 15:28
 */
public class CodeTest {
    @Test
    public void fileInputStreamTest(){
        try {
            FileInputStream fis = new FileInputStream("F:/fileIndex/a.txt");

            byte[] bytes = new byte[1024];
            int len = 0;

            while ((len = fis.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, len));
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void copyFile(String sourcePath, String targetPath){
        try {
            FileInputStream fis = new FileInputStream(sourcePath);
            FileOutputStream fos = new FileOutputStream(targetPath);

            byte[] bytes = new byte[1024];
            int len;

            while ((len = fis.read(bytes)) != -1){
                fos.write(bytes, 0, len);
            }
            fos.flush();

            fos.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void copyTest(){
        copyFile("F:/fileIndex/泳装索米.jpg", "F:/泳装索米.jpg");
    }

    @Test
    public void fileReaderTest(){
        try {
            FileReader fr = new FileReader("F:/fileIndex/a.txt");

            char[] chars = new char[1024];
            int len;

            while ((len = fr.read(chars)) != -1){
                System.out.println(new String(chars, 0, len));
            }

            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bufferedInputStreamTest(){
        try {
            FileInputStream fis = new FileInputStream("F:/fileIndex/a.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] bytes = new byte[1024];
            int len;

            while ((len = bis.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, len));
            }

            bis.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bufferedOutputStreamTest(){
        try {
            FileOutputStream fos = new FileOutputStream("F:/fileIndex/data.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bos.write("hhhasdadhhhh".getBytes());
//            bos.flush();

            bos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void inputStreamReaderTest(){
        try {
            FileInputStream fis = new FileInputStream("F:/fileIndex/中文.txt");
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

            char[] chars = new char[1024];
            int len;

            while ((len = isr.read(chars)) != -1){
                System.out.println(new String(chars, 0, len));
            }

            isr.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void outputStreamReaderTest(){
        try {
            FileOutputStream fos = new FileOutputStream("F:/fileIndex/中文.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            osw.write("欧尼酱，咕噜咕噜");
            osw.flush();

            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printStreamTest(){
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String str;

            while ((str = br.readLine()) != null){
                System.out.println(str);
            }

            br.close();
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dataOutputStreamTest(){
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("F:/fileIndex/data.txt"));

            dos.writeBoolean(true);

            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dataInputStreamTest(){
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("F:/fileIndex/data.txt"));

            System.out.println(dis.readBoolean());

            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fileRecursive(File file){
        if (file.isFile()) {
            System.out.println("文件：\t" + file.getAbsolutePath());
        } else {
            System.out.println("文件夹：\t" + file.getAbsolutePath());
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    fileRecursive(f);
                }
            }
        }
    }

    @Test
    public void fileTest(){
        //打印D盘所有文件
        fileRecursive(new File("D:/_Game"));
    }

    @Test
    public void serializeTest(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("F:/fileIndex/user.txt"));

            ReflectUser user = new ReflectUser("jjj", 20);

            oos.writeObject(user);

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserializeTest(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("F:/fileIndex/user.txt"));

            ReflectUser user = (ReflectUser) ois.readObject();

            System.out.println(user);

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * mode:
     *  r：只读
     *  rw：读写
     *  rwd：读写；同步文件内容的更新
     *  rws：读写；同步文件内容和元数据的更新
     */
    @Test
    public void randomAccessFileReadTest(){
        try {
            RandomAccessFile raf = new RandomAccessFile("F:/fileIndex/a.txt", "r");

            raf.seek(14);

            byte[] bytes = new byte[1024];
            int len;

            while ((len = raf.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, len));
            }

            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void randomAccessFileWriteTest(){
        try {
            RandomAccessFile raf = new RandomAccessFile("F:/fileIndex/a.txt", "rw");

            raf.seek(raf.length());

//            raf.seek(0);
//            raf.seek(raf.length());

            raf.write("\nrandomAccessFileWriteTest".getBytes());

            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
