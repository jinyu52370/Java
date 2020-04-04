# Java IO流

## 概述

- Stream 可理解为 pipe 
- 在计算机所有的数据都是以文件的格式存储的
  - 在Java中认为文件是一个Object
- Class类 --> 对象 -->*.class在内存中的表现形式
  - new 类(); jvm --> class文件 --> Class对象

------

## File

### File类的构造方法

```java
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
```

### File类的成员方法

创建文件

```java
@Test
public void createFile() throws IOException {
    File file = new File("F:\\fileIndex\\b.txt");
    System.out.println(file.createNewFile());

    //若未指定创建文件的路径，则创建在项目路径(相对路径)
    File file1 = new File("bb.txt");
    System.out.println(file1.createNewFile());

    //创建文件时路径必须要存在 -- java.io.IOException: 系统找不到指定的路径。
}
```

创建文件夹

```java
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
```

删除文件

```java
@Test
public void delete(){
    System.out.println(new File("bb.txt").delete());

    //delete的文件夹下不能有文件/文件夹
    System.out.println(new File("directory").delete());
}
```

重命名文件/文件夹

- File的改名在同一个文件夹下? 改名 : 移动并改名

```java
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
```

判断

```java
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
```

获取

- 相对路径：相对于当前项目
- 绝对路径：从磁盘的盘符开始

```java
@Test
public void get(){
    File file = new File("F:\\fileIndex\\a.txt");
    File dir = new File("F:\\fileIndex");

    System.out.println("绝对路径：" + file.getAbsolutePath());
    System.out.println("相对路径：" + file.getPath());
    System.out.println("名称：" + file.getName());
    System.out.println("字节数：" + file.length());
    System.out.println("文件夹字节数：" + dir.length());
    System.out.println("最后一次修改时间(ms)：" + DateFormat.getDateTimeInstance().format(new 				Date(file.lastModified()))) ;

    //文件夹下所有文件/文件夹的名称集合 -- List<String>
    System.out.println("遍历文件/文件夹名");
    Arrays.stream(dir.list()).forEach(System.out::println);

    //文件夹下所有文件/文件夹的File集合 -- List<File>
    System.out.println("遍历文件/文件夹名");
    Arrays.stream(dir.listFiles()).map(File::getName).forEach(System.out::println);
}
```

练习：判断 D:\壁纸 是否有 色图.png

```java
@Test
public void exercise(){
    File dir = new File("D:\\壁纸");

    String pictureName = "色图.png";
    if (Arrays.stream(dir.listFiles()).noneMatch(file -> pictureName.equals(file.getName()))){
        System.out.println("D:\\壁纸中没有" + pictureName);
    } else {
        System.out.println(new File(dir, pictureName).getAbsolutePath());
    }

    /*
    	Arrays.stream(dir.listFiles()).filter(
    			file -> "色图.png".equals(file.getName())
        	).forEach(System.out::println);
    */
}
```

练习：批量修改 F:\\fileIndex下的文件名：均加上“批量修改”

```java
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
```

------

## IO

- 流向：(无论输入流或输出流均相对于当前程序)
  - 输入流(read) -- input
  - 输出流(write) -- output
- 传输的内容：
  - 字节数据：图片、音频、视频
    - 字节输入流：InputStream
    - 字节输出流：OutputStream
  - 字符数据：abcdef123
    - 字符输入流：Reader
    - 字符输出流：Writer
- 无论字节流还是字符流，底层流动的均是字节！(字符流是对字节流的包装)

### 常用API

练习：通过IO流往文件中写入“Hello IO”

- 字节输出流

```java
@Test
public void test() throws IOException {
    String data = "Hello IO";
    FileOutputStream fos = new FileOutputStream("F:\\fileIndex\\a.txt");

    //write(byte b[])
    fos.write(data.getBytes());

    //write(int b) -- ASCII code
    fos.write(97);

    //write(byte b[], int off, int len) -- 写入 Hell
    fos.write(data.getBytes(), 0, 4);

    fos.close();
}
```

字节输入流

```java
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
```

字符输出流

```java
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
```

字符输入流

```java
@Test
public void test3() throws IOException {
    FileReader fr = new FileReader("F:\\fileIndex\\a.txt");

    fr.skip(1);
    System.out.println(fr.read());

    fr.close();
}
```

### 不常用API

#### LineNumberReader

```java
@Test
public void test() throws IOException {
    LineNumberReader lnr = new LineNumberReader(new FileReader("F:\\fileIndex\\a.txt"));

    String line;
    while ((line = lnr.readLine()) != null){
        System.out.println(lnr.getLineNumber() + "\t" + line);
    }

    lnr.close();
}
```

#### 数据输入输出流

- 数据输出流 -- DataOutputStream

```java
@Test
public void test1() throws IOException {
    DataOutputStream dos = new DataOutputStream(new FileOutputStream("F:\\fileIndex\\data.txt"));

    dos.writeByte(97);
    dos.writeUTF("zxc");
    dos.writeBoolean(false);

    dos.close();
}
```

- 数据输入流 -- DataInputStream

```java
@Test
public void test2() throws IOException {
    DataInputStream dis = new DataInputStream(new FileInputStream("F:\\fileIndex\\data.txt"));
    System.out.println(dis.read());
    System.out.println(dis.readUTF());
    System.out.println(dis.readBoolean());

    dis.close();
}
```

#### 内存操作流

- 操作字节数组

```java
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
```

- 操作字符数组

```java
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
```

- 操作字符串

```java
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
```

#### 打印流

打印流特殊功能：

1. 可以打印所有的数据：
   - 基本类型
   - 引用类型：该类型对象的toString()方法
2. 可以自动刷新

- 字节打印流

```java
@Test
public void test6() throws IOException {
    PrintStream ps = new PrintStream(new FileOutputStream("a.txt"),true);
    ps.println(123);
}
```

- 字符打印流

```java
@Test
public void test7() throws IOException {
    //字符 -- 自动刷新构造方法
    PrintWriter pwfw = new PrintWriter(new FileWriter("F:\\fileIndex\\a.txt"),true);
    //字节 -- 自动刷新构造方法
    PrintWriter pwfos = new PrintWriter(new FileOutputStream("F:\\fileIndex\\a.txt"),true);					//pw.write("PrintWriter");

    pwfw.println(true);
    pwfw.println(1);
    pwfw.println(1.1f);
    pwfw.println(12.3);
    pwfw.println("123");
    pwfw.println(new Object());
    pwfw.println('c');

    //pw.flush();
    //pw.close();
}
```

#### 标准输入输出流

```java
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
```

#### 合并流

```java
@Test
public void test9() throws IOException {
    //参数在前的先被打印
    SequenceInputStream si = new SequenceInputStream(
        new FileInputStream("src\\com\\jjj\\IteratorTest.java"), 
        new FileInputStream("a.txt"));

    OutputStream os = new FileOutputStream("sequence.txt");
    int len;
    byte[] b = new byte[1024];
    while ((len = si.read(b)) != -1) {
        os.write(b, 0, len);
    }
    os.close();
    si.close();
}
```

#### properties

- 读取file.properties属性列表，生成属性文件a.properties

```java
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
```

#### serializable

```java
@Test
public void serializableTest() throws IOException, ClassNotFoundException {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt"));
    oos.writeObject(Arrays.asList("jjj",20,"M"));
    oos.close();

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"));
    System.out.println(ois.readObject());
    ois.close();
}
```

#### 随机访问文件流

```java
@Test
public void test10(){
    File file = new File("F:" + File.separator + "fileIndex" + File.separator + "a.txt");
	//TODO
}
```