package com.jinyu.io;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/2/20 11:31
 */
public class FileTest {
    //region File类的构造方法
    /**
     * File类的构造方法
     */
    @Test
    public void test(){
        //File(String pathname) -- 根据一个路径得到File对象
        File file = new File("F:\\fileIndex\\a.txt");

        //File(String parent, String child) -- 根据一个目录和一个子文件/目录得到File对象
        //Java认为文件夹是一种特殊的文件
        File file1 = new File("F:\\fileIndex", "a.txt");

        //File(File parent, String child) -- 根据一个父File对象和一个子文件/目录得到File对象
        File parentFile = new File("F:\\fileIndex");
        File file2 = new File(parentFile, "a.txt");
    }
    //endregion

    //region File类的成员方法

    /**
     * 创建文件
     */
    @Test
    public void createFile() throws IOException {
        File file = new File("F:\\fileIndex\\b.txt");
        System.out.println(file.createNewFile());

        //若未指定创建文件的路径，则创建在项目路径
        File file1 = new File("bb.txt");
        System.out.println(file1.createNewFile());

        //创建文件时路径必须要存在 -- java.io.IOException: 系统找不到指定的路径。
    }

    /**
     * 创建文件夹
     */
    @Test
    public void createDirectory(){
        File file = new File("F:\\fileIndex\\directory");
        System.out.println(file.mkdir());

        //mkdir() -- 无法创建多层文件夹
        File file1 = new File("F:\\fileIndex\\directory1\\directory2");
        System.out.println(file1.mkdir());

        //mkdirs() -- 创建多层文件夹
        System.out.println(file1.mkdirs());

        //若未指定创建文件的路径，则创建在项目路径
        File file2 = new File("directory\\ddd");
        file2.mkdirs();
    }

    /**
     * 删除文件
     */
    @Test
    public void delete(){
        System.out.println(new File("bb.txt").delete());

        //delete的文件夹下不能有文件/文件夹
        System.out.println(new File("directory").delete());
    }

    /**
     * 重命名文件/文件夹
     *  File的改名在同一个文件夹下? 改名 : 移动并改名
     */
    @Test
    public void rename() throws IOException {
        File file = new File("name");
        System.out.println(file.mkdir());

        System.out.println(file.renameTo(new File("rename")));

        //相当于移动文件/文件夹
        File fileMove = new File("test");
        System.out.println(fileMove.createNewFile());
        fileMove.renameTo(new File("F://testRename.txt"));
    }

    /**
     * 判断
     */
    @Test
    public void is(){
        File file = new File("F:\\fileIndex\\a.txt");
        System.out.println("是否是目录？\t" + file.isDirectory());
        System.out.println("是否是文件？\t" + file.isFile());
        System.out.println("是否存在？\t" + file.exists());
        System.out.println("是否可读？\t" + file.canRead());
        System.out.println("是否可写？\t" + file.canWrite());
        System.out.println("是否隐藏？\t" + file.isHidden());
        System.out.println("是否可执行？\t" + file.canExecute());
    }

    /**
     * 获取
     *  相对路径：相对于当前项目
     *  绝对路径：从磁盘的盘符开始
     */
    @Test
    public void get(){
        File file = new File("F:\\fileIndex\\a.txt");
        File dir = new File("F:\\fileIndex");

        System.out.println("绝对路径：" + file.getAbsolutePath());
        System.out.println("相对路径：" + file.getPath());
        System.out.println("名称：" + file.getName());
        System.out.println("字节数：" + file.length());
        System.out.println("文件夹字节数：" + dir.length());
        System.out.println("最后一次修改时间(ms)：" + DateFormat.getDateTimeInstance().format(new Date(file.lastModified()))) ;

        //文件夹下所有文件/文件夹的名称集合 -- List<String>
        System.out.println("遍历文件/文件夹名");
        Arrays.stream(dir.list()).forEach(System.out::println);

        //文件夹下所有文件/文件夹的File集合 -- List<File>
        System.out.println("遍历文件/文件夹名");
        Arrays.stream(dir.listFiles()).map(File::getName).forEach(System.out::println);
    }

    //endregion

    //region 练习：判断 D:\壁纸 是否有 色图.png
    @Test
    public void exercise(){
        File dir = new File("D:\\壁纸");

        String pictureName = "色图.png";
        if (Arrays.stream(dir.listFiles()).noneMatch(file -> pictureName.equals(file.getName()))){
            System.out.println("D:\\壁纸中 没有 " + pictureName);
        } else {
            System.out.println(new File(dir, pictureName).getAbsolutePath());
        }

//        Arrays.stream(dir.listFiles()).filter(file -> "色图.png".equals(file.getName())).forEach(System.out::println);
    }
    //endregion

    //region 练习：批量修改 F:\\fileIndex下的文件名：均加上“批量修改”
    @Test
    public void batchUpdate(){
        String dirName = "F:\\fileIndex";
        String batchUpdateName = "批量修改";

        Arrays.stream(new File(dirName).listFiles()).map(file -> file.renameTo(new File(
                dirName + "\\"
                        + file.getName().substring(0, file.getName().indexOf("."))
                        + batchUpdateName
                        + file.getName().substring(file.getName().indexOf("."))))
        ).forEach(System.out::println);
    }
    //endregion
}
