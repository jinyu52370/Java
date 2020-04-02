# JavaSE

## 对象类型转换：

- 高级的基本数据类型 ——> 低级数据类型
  - 强制类型转化：int i  =  (int) long;

- 低级的基本数据类型 ——> 高级数据类型
  - 自动类型转化：long l  =  int;

- 父类 ——> 子类
  - 向下转型，强制，用instanceof判断：Student s = (Student) Person;
- 子类 ——> 父类
  - 向上转型，自动：Person p = Student;

## == & equals：

- equals可重写

- 对象：
  - 特殊的类，如String、File、Date
    -  == 比较的是对象(对象的地址)
    - equals比较的是内容
  - 其他的普通类的对象
    - == 和 equals均比较的是对象(对象的地址)

## String对象的创建

- 字面量创建对象时，只在常量池中创建一个对象；new的时候，常量池创建对象，堆中也创建对象。

  **所以字面量比new更省内存**

- 字面量创建String对象

  ```java
  //常量池中添加"abc"对象，返回引用地址给s1对象
  String s1 = "abc";
  
  //通过equals()方法判断常量池中已有值为abc的对象，返回相同的引用
  String s2 = "abc"; 
  
  //true
  System.out.println(s1 == s2);
  ```

- new创建String对象

  ```java
  //在常量池中添加"def"对象，在堆中创建值为"def"的对象s3，返回指向堆中s3的引用
  String s3 = new String("def");
  
  //常量池中已有值为"def"的对象，不做处理，在堆中创建值为"def"的对象，返回指向堆中s4的引用
  String s4 = new String("def");
  ```

  ```java
  //经过JVM优化，直接在常量池中添加"xy"对象
  String s5 = "x" + "y";
  ```

  ```java
  //通过StringBuilder实现，在常量池中添加"1"和"2"对象，在堆中创建值为"112"的对象，把引用地址给s6
  String s6 = new String("1") + new String("1") + new String("3");
  ```


## 单例(Singleton)设计模式

- 饿汉式

  ```java
  public class EagerSingleton {
      /**
       * 私有构造：调用这个类时无法使用new来创建实例
       */
      private EagerSingleton(){}
  
      /**
       * 私有的Singleton类型的类变量
       */
      private static EagerSingleton eagerSingleton = new EagerSingleton();
  
      public static EagerSingleton getInstance(){
          return eagerSingleton;
      }
  }
  ```

- 懒汉式

  ```java
  public class LazySingleton {
      /**
       * 私有构造
       */
      private LazySingleton(){}
  
      /**
       * 私有的Singleton类型的类变量
       */
      private static LazySingleton lazySingleton = null;
  
      public static LazySingleton getInstance(){
          if (lazySingleton == null){
              lazySingleton = new LazySingleton();
          }
          return lazySingleton;
      }
  }
  ```

## new对象时的构造顺序

1. 类的属性默认初始化和显示初始化
2. 静态代码块(只执行一次；多个按顺序执行)
3. 执行代码块(多个按顺序执行)
4. 执行构造器

## 模板设计模式

- 抽象类/模板

  ```java
  public abstract class AbstractTimeTemplate {
      /**
       * 子类具体的代码
       */
      public abstract void code();
  
      /**
       * 获得代码的执行时间
       */
      public final void getTime(){
          long start = System.currentTimeMillis();
          code();
          long end = System.currentTimeMillis();
  
          System.out.println("code()执行时间：" + (end - start));
      }
  }
  ```

- 子类

  ```java
  public class CodeTest extends AbstractTimeTemplate{
      @Override
      public void code() {
          Stream.iterate(0, t -> t + 1).limit(1024).forEach(System.out::println);
	 	}
  }
  ```

## 工厂方法(FactoryMethod)

## 可以使用内部类来变相实现类的多重继承

## Java集合

集合类存放于java.util包中

- 只能存放对象，如int类型的1放入集合，自动转换为Integer

### 类型

**set**

- HashSet <- Set <- Collection
  - 无序、不可重复、非线程安全、可null
  - 存入一个元素时，会调用该对象的hashCode()方法得到hashCode值，根据**hashCode值**决定存放位置
- TreeSet <- NavigableSet <- SortedSet <- Set <- Collection
  - 有序
  - 自然排序（调用接元素的compareTo(Objecet obj)方法，比较后asc）
    - this > obj 1
    - this < obj -1
    - this = obj 0
    - 必须放入同样的类的对象（泛型约束）
  - 定制排序

**list**

- ArrayList <- List <- Collection
  - sublist(, )  [ )
  - 非线程安全（Vector线程安全，但为了保证List线程安全也不推荐使用Vector）

**map**

- HashMap
  - 非线程安全
  - 允许null作为key/value
- Hashtable（古老，不建议使用）
  - 线程安全
  - 不允许null作为key/value
- TreeMap
  - 保持有序
  - 自然排序：所有的key继续实现Comparable接口且为同一类的对象，否则抛出ClassCastException
  - 定制排序：创建一个TreeMap时，传入一个Comparator对象，key无需实现Comparable接口

## java中的泛型，只会在编译阶段有效，不会进入到运行时阶段

## 每个枚举对象都是单例模式

## Java的IO

- 文件流：基于文件的操作
- 缓冲流：基于内存的操作

## 文件编码格式

TXT/Java编码

1. ISO8859-1，西欧编码，不适于汉字
2. GBK和UTF-8，中英皆可

## 反射

前提：已经加载过这个类，即可通过类名来寻找这个类的相关信息

### java.lang.Class

### java.lang.reflect.Method

### java.lang.reflect.Field

### java.lang.reflect.Constructor

## 多线程

### 创建线程的两种方式

1. 继承Thread类
   - 定义子类继承Thread类
   - 重写 run() 方法
   - 创建了Tread子类对象，即创建了线程对象
   - 调用线程对象 start()方法：启动线程，调用run()方法
2. （常用）实现Runnable接口

### 实现接口方式的好处

1. 避免了单继承的局限性
2. 多个线程可以共享同一个接口实现类的对象，非常适合多个相同线程处理同一份资源