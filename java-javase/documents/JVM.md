# JVM

## JVM体系结构概述

​		JVM位置：硬件系统 -> 操作系  统 -> Java Virtual Machine

<img src="https://gitee.com/jinyu52370/images/raw/master/images/JVM体系结构概览.png" alt="JVM体系结构概览" style="zoom:75%;" />

-----

## 类装载器ClassLoader

<img src="https://gitee.com/jinyu52370/images/raw/master/images/ClassLoader.png" alt="ClassLoader" style="zoom:60%;" />

### sun.misc.Launcher

- 是java虚拟机的入口应用

<img src="https://gitee.com/jinyu52370/images/raw/master/images/ClssLoader1.png" alt="ClssLoader1" style="zoom:60%;" />

- 虚拟机自带的加载器
  1. 启动类加载器（Bootstrap）C++
  2. 扩展类加载器（Extension）Java
  3. 应用程序类加载器（AppClassLoader）（一般定义的类使用此加载器）
     - Java也叫系统类加载器（System ClassLoader），加载当前应用的classpath的所有类
- 用户自定义加载器
  4. java.lang.ClassLoader的子类，用户可以定制类的加载方式

### 双亲委派机制

​		当一个类收到了类加载请求，他首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成，每一个层次类加载器都是如此，因此所有的加载请求都应该传送到启动类加载器中，只有当父类加载器反馈自己无法完成这个请求的时候（在它的加载路径下没有找到所需加载的Class），子类加载器才会尝试自己去加载。

​		采用双亲委派的一个好处是比如加载位于 rt.ar包中的类 java. lang object，不管是哪个加载器加载这个类，最终都是委托给顶层的启动类加载器进行加载，这详就保证了使用不同的类加载器最终得到的都是同样一个 Object对象

加载一个类时，从最**顶部**开始加载：

- 即 Bootstrap -> Extension -> AppClassLoader/System ClassLoader

### 沙箱安全机制

​		保证java源码不受污染，干净一致

-----

## Execution Engine执行引擎

​		负责解释命令，提交操作系统执行

-----

## Native Interface本地接口

​		本地接口的作用是融合不同的编程语言为Java所用，它的初衷是融合C/C++程序，Java诞生的时候是C/C++横行的时候，要想立足，必须有调用C/C++程序，于是就在内存中专门开辟了一块区域处理标记为native的代码，它的具体做法是 Native method stack中登记native方法，在 Execution Engine执行时加载 native libraies。目前该方法使用的越来越少了，除非是与硬件有关的应用，比如通过Java程序驱动打印机或者Java系统管理生产设备，**在企业级应用中已经比较少见**。因为现在的异构领域间的通信很发达，比如可以使用Socket通信，也可以使用 Web service等等，不多做介绍。

-----

## Native Method Stack本地方法栈

​		它的具体做法是 Native method stack中登记 native方法，在 ExecutionEngine执行时加载本地方法库

-----

## Program Counter Register程序计数器

​		每个线程都有一个程序计数器，是线程私有的，就是一个指针指向方法区中的方法字节码（用来存储指向下一条指令的地址，也即将要执行的指令代码），由执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不记。这块内存区域很小，它是当前线程所**执行的字节码的行号指示器**，字节码解释器通过改变这个计数器的值来选取下一条需要执行的字节码指令。如果执行的是一个 Native方法，那这个计数器是空的。用以完成分支、循环、跳转、异常处理、线程恢复等基础功能。不会发生内存溢出（Outofmemory=00M）错误

-----

## Method Area方法区

- 所有线程共享
- 存在gc

​		供各个线程共享的运行时的内存区域。**它存储了每一个类的结构信息**，例如运行时常量池（Runtime Constant Pool）、字段和方法数据、构造函数和普通方法的**字节码内容**。方法区是规范，在不同虚拟机里实现是不一样的，最典型的就是（java7）永久代（PermGen Space）和（java8）元空间（Metaspace）

​		**但，实例变量存在堆内存中，和方法区无关**

-----

## Java栈

- 栈管运行，堆管存储

​		栈也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命期是跟随线程的生命期，线程结束栈内存也就释放，对于栈来说**不存在**垃圾回收问题，只要线程一结束该栈就Over，生命周期和线程一致，是**线程私有**的。8种基杰类型的变量 + 对象的引用变量 + 实例方法都是在函数的栈内存中分配。

栈帧中主要保存3类数据：

- 本地变量（ Local variables）：输入参数和输出参数以及方法内的变量

- 栈操作（ Operand Stack）：记录出栈、入栈的操作

- 栈帧数据（ Frame Data）：包括类文件、方法等等

  

  - 局部变量表
  - 操作数栈
  - 指向运行时常量池的引用
  - 方法返回地址
  - 动态链接

栈运行原理:

​		栈中的数据都是以栈帧（ Stack frame）的格式存在，栈帧是一个内存区块，是一个数据集，是一个有关方法（ Method和运行期数据的数据集，当一个方法A被调用时就产生了一个栈帧F1，并被压入到栈中，A方法又调用了B方法，于是产生栈帧F2也被压入栈，B方法又调用了C方法，于是产生栈帧F3也被压入栈...执行完毕后，先弹出F3栈帧，再弹出F2栈帧，再弹出F1栈帧...

​		遵循“先进后出”/“后进先出”原则。

​		每个方法执行的同时都会创建一个栈帧，用于存储局部变量表、操作数栈、动态链接、方法出口等信息，每一个方法从调用直至执行完毕的过程，就对应着一个栈帧在虚拟机中入栈到出栈的过程。栈的大小和具体JWM的实现有关，通常在256K~756K之间，约等于1Mb左右。

<img src="https://gitee.com/jinyu52370/images/raw/master/images/stack.png" alt="stack" style="zoom:70%;" />

### 栈 + 堆 + 方法区的交互关系

<img src="https://gitee.com/jinyu52370/images/raw/master/images/栈_堆_方法区交互关系.png" alt="栈_堆_方法区交互关系" style="zoom:70%;" />

```java
Person p1 = new Person();
Person p2 = new Person();
Person p3 = new Person();
```

HotSpot是使用指针的方式来访问对象

栈：reference存储的是对象的地址

- p1、p2、p3

堆：存放对象实例数据、访问类元数据的地址

- new Person()

方法区：对象类型数据/类模板

- Class<Person>

-----

## heap堆

- 所有线程共享
- 存在gc

​		一个JVM实例只存在一个堆内存，堆内存的大小是可以调节的。类加载器读取了类文件后，需要把类、方法、常变量放到堆内存中，保存所有引用类型的真实信息，以方便执行器执行。

### **堆内存**逻辑上分为：

Young/New新生区

- Eden伊甸园区
- 幸存者零区
- 幸存者一区

Old/Tenure养老区

Perm永久区（java7）/元空间（java8）

<img src="https://gitee.com/jinyu52370/images/raw/master/images/堆内存的逻辑划分.png" alt="堆内存的逻辑划分" style="zoom: 55%;" />

### 堆溢出例子：

```java
while(true){
	new Person();
}
```

伊甸园区满了，开启GC == YGC == 轻GC，基本全部清空；

S0 = from，S1 = to；

交换：from区和to区的位置和名分不是固定的，每次GC后会交换：GC之后有交换，谁空谁是to；

未被回收的进入S0；

S0满了进入S1；

S1满了进入old；

Old满了，开启GC == F（full）GC == 重GC；

FGC多次，throws java.lang.OutOfMemoryError:Java heap space 异常。

​		新生区是类的诞生、成长、消亡的区域，一个类在这里产生，应用，最后被垃圾回收器收集，结束生命。新生区又分为两部分：伊甸区（ Eden space）和幸存者区（ Survivor pace），所有的类都是在伊甸区被new出来的。幸存区有两个：0区（ Survivor 0 space）和1区（ Survivor 1 space）。当伊甸园的空间用完时，程序又需要创建对象，JVM的垃圾回收器将对伊甸园区进行垃圾回收（ Minor GC），将伊甸园区中的不再被其他对象所引用的对象进行销毁。然后将伊甸园中的剩余对象移动到幸存0区。若幸存0区也满了，再对该区进行垃圾回收，然后移动到1区。那如果1区也满了呢？再移动到养老区。若养老区也满了，那么这个时候将产生 MajorCA（FullGC），进行养老区的内存清理。若养老区执行了FullGC之后发现依然无法进行对象的保存，就会产生OOM异常“ OutOfMemoryError”。

​		如果出现 java.lang.OutOfMemoryError: Java heap space异常，说明Java虚拟机的堆内存不够。原因有二:

1. Java虚拟机的堆内存设置不够，可以通过参数-Xms、-Xmx来调整。
2. 代码中创建了大量大对象，并且长时间不能被垃圾收集器收集（存在被引用）

### 堆从GC的角度还可以划分为：新生区和养老区

![Heap堆内存物理划分](https://gitee.com/jinyu52370/images/raw/master/images/Heap堆内存物理划分.png)

### MinorGC的过程（复制 -> 清空 -> 互换）

1. Eden、 Survivor From复制到 Survivor To，年龄+1
   - 首先，当Eden区满的时候会触发第一次GC把还活着的对象拷贝到 Survivor From区，当Eden区再次触发GC的时候会扫描Eden区和From区域对这两个区域进行垃圾回收，经过这次回收后还存活的对象则直接复制到To区域（如果有对象的年龄已经达到了老年的标准，则复制到老年代区），同时把这些对象的年龄+1
2. 清空Eden、 Survivor From
   - 然后，清空Eden和 Survivor From中的对象，也即复制之后有交换，谁空谁是To
3.  Survivor和 Survivor From互换
   - 最后， Survivor To和 Survivor From互换，原Survivor To成为下一次GC时的 Survivor From区。部分对象会在From和To区域中复制来复制去如此交换15次（由M参数 MaxTenuringThreshold决定，这个参数默认是15），最终如果还是存活，就存入到老年代

<img src="https://gitee.com/jinyu52370/images/raw/master/images/Sun HotSpot™内存管理.png" alt="Sun HotSpot™内存管理" style="zoom:80%;" />

经研究：**不同对象的生命周期不同**，98%的对象是临时对象

​		实际而言，方法区（ Method Area）和堆一样，是各个**线程共享**的内存区域，它用于存储虚拟机加载的：类信息+普通常量+静态常量+编译器编译后的代码等等，虽然JVM规范将方法区描述为堆的一个逻辑部分，但它却还有个**别名叫做 Non-Heap（非堆）**，目的就是要**和堆分开**

​		对于 Hotspot虚拟机，很多开发者习惯将方法区称之为“永久代Parmanent Gen）”，但严格本质上说两者不同，或者说使用永久代来实现方法区而已，永久代是方法区（相当于是一个接口 interface）一个实现，**jdk17的版本中，已经将原本放在永久代的字符串常量池移走。**

### 永久区（java7之前有）

​		永久存储区是一个常驻内存区域，用于存放JDK自身所携带的Class， Interface的元数据（jkd/jre/lib/rt.jar），也就是说它存储的是运行环境必须的类信息，被装载进此区域的数据是不会被垃圾回收器回收掉的，关闭JWM才会释放此区域所占用的内存

### 元空间（java8）

​		在Java8中，永久代已经被移除，被一个称为**元空间**的区域所取代。元空间的本质和永久代类似。

​		元空间与永久代之间最大的区别在于：**永久带**使用的JVM的**堆内存**，但是java8以后的**元空间**并不在虚拟机中而是使用本机**物理内存**。

​		因此，默认情况下，**元空间的大小仅受本地内存限制**。类的元数据放入 native memory，字符串池和类的静态变量放入java堆中，这样可以加载多少类的元数据就不再由 MaxPerm Size控制，而由系统的实际可用空间来控制。

### 堆参数调优

Young（-Xmn）

- n：new

JVM Heap（-Xms -Xmx）

- s：start
- x：max

|                     |                                        |
| :------------------ | -------------------------------------- |
| -Xmx                | 最大分配内存，默认为物理内存的1/4      |
| -Xms                | 设置初始分配大小，默认为物理内存的1/64 |
| -XX:+PrintGCDetails | 输出详细的GC处理日志                   |

-Xms1024m -Xmx1024m -XX:+PrintGCDetails

![](https://gitee.com/jinyu52370/images/raw/master/images/OOM.png)

规律：

[GC/Full GC	[名称：GC前内存占用	->	GC后内存占用	(该区内存总大小)]]

### GC

JVM在进行GC时，并非每次都对上面三个内存区域一起回收，大部分时候回收的都是新生区

类型：

- Minor GC：只针对新生区的GC，指发生在新生区的垃圾收集动作，因为大多数Java对象存活率都不高，所以Minor GC非常频繁，一般回收速度也很快。
- Major/Full GC：指发生在养老区的垃圾收集动作，出现了FGC，经常会伴随至少一次的Minor GC（并非绝对）。FGC的速度一般比Minor GC慢上10倍以上（养老区占2/3，扫描更慢）。

<img src="https://gitee.com/jinyu52370/images/raw/master/images/垃圾收集机制.png" alt="垃圾收集机制" style="zoom: 80%;" />

### 四大算法：

1. 引用计数法（**JVM的实现一般不采用这种方法**）

   - 原理：每有一个引用指向一个对象，它的GC引用计数器就加1，当为0时就发生GC
   - 缺点：
     - 每次对象赋值时均要维护引用计数器，且计数器本身也有一定的消耗
     - 较难处理循环引用

2. 复制算法（Copying）

   - 在**新生代**中使用Minor GC

   - 原理：Minor GC会把Eden中的所有活的对象都移到 Survivor区域中，如果 Survivor区中放不下，那么剩下的活的对象就被移到Old Generation中，也即一旦收集后，**Eden是就变成空的了**。

     ​		**解释①**：当对象在Eden（包括一个 Survivor区域，这里假设是from区域）出生后，在经过一次Minor gc后，如果对象还存活，并且能够被另外一块 Survivor区域所容纳（上面已经假设为from区域，这里应为to区域，即to区域有足够的内存空间来存储Eden和from区琙中存活的对象），则使用**复制算法**将这些仍然还存活的对象复制到另外一块 Surviνor区域（即to区域）中，然后清理所使用过的Eden以及 Survivor区域（即from区域），并且将这些对象的年龄设置为1，以后对象在 Survivor区每熬过一次 Minor GC，就将对象的年龄+1，当对象的年龄达到某个值时（默认是15岁，通过-XX:MaxTenuringThreshold来设定参数），这些对象就会成为老年代。

     ​		-XX:MaxTenuringThreshold——设置对象在新生代中存活的次数

     ​		**解释②**：在GC开始的时候，对象只会存在于Eden区和名为“From”的 Survivor区， Survivor区“To”是空的。紧接着进行GC，Eden区中所有存活的对象都会被复制到“To”，而在“From”区中，仍存活的对象会根据他们的年龄值来决定去向。年龄达到一定值（年龄阈值，可以通过-XX: MaxTenuring Threshold来设置）的对象会被移动到年老代中，没有达到阈值的对象会被复制到“To”区域。经过这次GC后，Eden区和From区已经被清空。这个时候，“From”和“To”会交换他们的角色，也就是新的“To”就是上次GC前的“From”，新的“From”就是上次GC前的“To”。不管怎样，都会保证名为To的 Survivor区域是空的。 Minor GC会一直重复这样的过程，直到“To”区被填满，“To”区被填满之后，会将所有对象移动到年老代中。

     ​		复制算法的基本思想就是将内存分为两块（Eden + From 和 To），每次只用其中一块，当一块用完，就将还活着的对象复制到另一块上面。

   - 复制算法想要使用，**对象的存活率要非常低**，还需克服50%内存的浪费

   - 优点：复制算法不会产生内存碎片

   - 缺点：耗费空间

3. 标记清除（Mark-Sweep）

   - **养老区**一般是由标记清除或标记清除和标记整理的混合实现
   - 原理：算法分成标记和清除两个阶段，先标记出要回收的对象，然后统一回收这些对象

   <img src="https://gitee.com/jinyu52370/images/raw/master/images/标记清除.png" alt="标记清除" style="zoom:70%;" />

   - 优点：不需要额外空间
   - 缺点：
     - **两次扫描，耗时严重**，且进行GC时，需要停止应用程序，导致用户体验非常差
     - **会产生内存碎片**，为此JVM需要维持一个内存的空闲列表，且在分配数组对象时，不易寻找连续的内存空间

4. 标记压缩/整理（Mark-Compact）

   - **养老区**一般是由标记清除或标记清除和标记整理的混合实现
   - 原理：
     - 标记：与标记清除一样
     - 压缩：再次扫描，并往一端**滑动**存活对象
   - 优点：没有内存碎片，可以利用bump-the-pointer
   - 缺点：需要移动对象的成本

- 标记-清除-压缩（Mark-Sweep-Compact）：
  - 标记清除和标记压缩的结合
  - 和标记清除一致，当进行**多次GC**后再压缩
  - 优点：减少移动对象的成本
- 分代收集算法（最好的算法不是其中的一个，每个区用不同的算法）
  - 次数上频繁收集Young区
  - 次数上较少收集Old区
  - 基本不动元空间

**内存效率**：复制算法 > 标记清除算法 > 标记整理算法（此处的效率只是简单的对比时间复杂度，实际情况不一定如此）

**内存整齐度**：复制算法 = 标记整理算法 > 标记清除算法

**内存利用率**：标记整理算法 = 标记清除算法 > 复制算法

可以看岀，效率上来说，复制算法是当之无愧的老大，但是却浪费了太多内存，而为了尽量兼顾上面所提到的三个指标，标记整理/算法相对来说更平滑一些，但效率上依然不尽如人意，它比复制算法多了一个标记的阶段，又比标记/清除多了一个整理内存的过程

## JMM（Java Memory Model）

volatile是JVM提供的轻量级的同步机制

- 保证可见性
- **不保证**原子性
- 进制指令重排

​		JMM本身是一种抽象的概念，**并不真实存在**，它描述的是一组规则或规范，通过这组规范定义了程序中各个变量（包括实例字段，静态字段和构成数组对象的元素）的访问方式。

### JMM关于同步的规定：

1. 线程解锁前，必须把共享变量的值刷新回主内存
2. 线程加锁前，必须读取主内存的最新值到自己的工作内存
3. 加锁解锁必须是同一把锁

由于JVM运行程序的实体是线程，而每个线程创建时JVM都会为其创建一个工作内存（有些地方称为栈空间），工作内存是每个线程的**私有**数据区域，而Java内存模型中规定所有变量都存储在主内存，主内存是共享内存区域，所有线程都可以访问，**但线程对变量的操作（读取赋值等）必须在工作内存中进行，首先要将变量从主内存拷贝到的线程自己的工作内存空间，然后对变量进行操作，操作完成后再将变量写回主内存**，不能直接操作主内存中的变量，各个线程中的工作内存中存储着主内存中的**变量副本拷贝**，因此不同的线程间无法访问对方的工作内存，线程间的通信（传值）必须通过主内存来完成，其简要访问过程如下图：

<img src="https://gitee.com/jinyu52370/images/raw/master/images/JMM线程通信.png" alt="JMM线程通信" style="zoom:80%;" />

- 特征
  - 可见性
  - 原子性
  - 有序性

