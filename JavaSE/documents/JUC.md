# JUC (java.util.concurrent)

## 概念

### Process进程：

​		是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是操作系统结构的基础。在早期面向进程设计的计算机结构中，进程是程序的基本执行实体；在当代面向线程设计的计算机结构中，进程是线程的容器。程序是指令、数据及其组织形式的描述，进程是程序的实体。

### Thread线程：

​		是操作系统能够进行运算调度的最小单位。它被**包含在进程之中**，是进程中的**实际运作单位**。一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。在Unix System V及SunOS中也被称为轻量进程（lightweight processes），但轻量进程更多指内核线程（kernel thread），而把用户线程（user thread）称为线程。

​		线程是独立调度和分派的基本单位。线程可以为操作系统内核调度的内核线程，如Win32线程；由用户进程自行调度的用户线程，如Linux平台的POSIX Thread；或者由内核与用户进程，如Windows 7的线程，进行混合调度。

​		同一进程中的多条线程将共享该进程中的全部系统资源，如虚拟地址空间，文件描述符和信号处理等等。但同一进程中的多个线程有各自的调用栈（call stack），自己的寄存器环境（register context），自己的线程本地存储（thread-local storage）。

​		一个进程可以有很多线程，每条线程并行执行不同的任务。

​		在多核或多CPU，或支持Hyper-threading的CPU上使用多线程程序设计的好处是显而易见，即提高了程序的执行吞吐率。在单CPU单核的计算机上，使用多线程技术，也可以把进程中负责I/O处理、人机交互而常被阻塞的部分与密集计算的部分分开来执行，编写专门的workhorse线程执行密集计算，从而提高了程序的执行效率。

### Concurrent并发：

​		在操作系统中，是指一个时间段中有几个程序都处于已启动运行到运行完毕之间，且这几个程序都是在同一个处理机上运行，但任一个时刻点上只有一个程序在处理机上运行。

​		当有多个线程在操作时,如果系统只有一个CPU,则它根本不可能真正同时进行一个以上的线程，它只能把CPU运行时间划分成若干个时间段,再将时间段分配给各个线程执行，在一个时间段的线程代码运行时，其它线程处于挂起状。这种方式我们称之为并发

### Parallel并行：

​		在操作系统中是指，一组程序按独立异步的速度执行，无论从微观还是宏观，程序都是一起执行的。对比地，并发是指：在同一个时间段内，两个或多个程序执行，有时间上的重叠(宏观上是同时,微观上仍是顺序执行)。

​		当系统有一个以上CPU时,则线程的操作有可能非并发。当一个CPU执行一个线程时，另一个CPU可以执行另一个线程，两个线程互不抢占CPU资源，可以同时进行，这种方式我们称之为并行。

-----

## 三个包

java.util.concurrent

java.util.concurrent.atomic

java.util.concurrent.locks

-----

## 口诀

1. 高内聚低耦合前提下，线程操作资源类
2. 判断/干活/通知
3. 防止虚假唤醒

**synchronized实现同步的基础**：Java的每一个对象都可以作为锁：

- 对于普通同步方法，锁的是当前实例对象（对象锁）
- 对于同步方法块，锁的是synchronized括号里配置的对象
- 对于静态方法，锁的是当前类的Class对象（类锁）

-----

## 8lock

1. 标准访问，请问先打印邮件还是短信
2. 暂停4秒，调用邮件方法，请问先打印邮件还是短信
   - 一个对象里若有多个synchronized方法，有一时刻内，只要一个线程去调用其中的一个synchronized方法，其他的线程都只能等待：
   - 即，某一时刻内，只能有唯一的一个线程去访问只写synchronized方法
   - 锁的是当前对象this（对象锁），被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
3. 新增普通sayHello()方法，请问先打印邮件还是hello
   - 加普通方法后与同步锁无关
4. 两部手机，请问先打印邮件还是短信
   - 换成两个对象后，不是同一把锁了
5. 两个静态同步方法，同一部手机，请问先打印邮件还是短信
6. 两个静态同步方法，两部手机，请问先打印邮件还是短信
   - 静态同步方法，锁的是当前类的Class对象（类锁）
7. 一个静态同步方法，一个普通同步方法，同一部手机，请问先打印邮件还是短信
8. 一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信
   - 静态同步方法和非静态同步方法不是同一个锁，不会有竞态条件
   - 一旦一个静态同步方法获取锁后，其他无论是否属于同一个实例的静态同步方法均需等待该方法释放锁后才能获取锁

-----

## 两个三角

synchronized

- wait
- notify

Lock

- condition.await()
- condition.signalAll()

-----

## BlockingQueue

| 方法类型 | 抛出异常  |  特殊值  |  阻塞  |         超时         |
| :------: | :-------: | :------: | :----: | :------------------: |
|   插入   |  add(e)   | offer(e) | put(e) | offer(e, time, unit) |
|   移除   | remove()  |  poll()  | take() |   poll(time, unit)   |
|   检查   | element() |  peek()  | 不可用 |        不可用        |

抛出异常

- 当阻塞队列满时，再往队列里add插入元素会抛 IllegalStateException: Queue full
- 当阻塞队列空时，再往队列里 remove移除元素会抛 NoSuchElementException

特殊值

- 插入方法，成功ture失败false
- 移除方法，成功返回出队列的元素，队列里没有就返回null

一直阻塞

- 当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据或响应中断退出
- 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用

超时退出

- 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出

-----

## ThreadPool

为什么使用线程池？

- 例子：10年前单核CPU电脑，假的多线程，像马戏团小丑玩多个球，CPU需要来回切换，现在是多核电脑，多个线程各自跑在独立的CPU上，不用切换效率高。

- 线程池的优势:

  ​		线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了最大数量，超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行。

  - 它的主要特点为：线程复用；控制最大并发数；管理线程。
    - 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的销耗；
    - 提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行；
    - 提髙线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会销耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。

### ThreadPoolExecutor

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.acc = System.getSecurityManager() == null ?
            null :
            AccessController.getContext();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
```

- **corePoolSize**：线程池中的常驻核心线程数
- **maximumPoolSize**：线程池中能够容纳同时执行的最大线程数，次值必须大于等于1
- **keepAliveTime**：多余的空闲线程的存活时间，当前池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程为止
- **unit**：keepAliveTime的单位
- **workQueue**：任务队列，被提交但尚未被执行的任务
- **threadFactory**：表示生成线程池中工作线程的线程工厂，用创建线程，**一般默认即可**
- **handler**：拒绝策略，表示当队列满了，并且工作线程大于等于maximumPoolSize时如何来拒绝请求执行的runnable的策略

### 线程池底层工作原理

<img src="https://github.com/jinyu52370/Java/blob/master/JavaSE/documents/images/ThreadPool底层工作原理.png" alt="ThreadPool底层工作原理" style="zoom:80%;" />

<img src="https://github.com/jinyu52370/Java/blob/master/JavaSE/documents/images/线程池的主要处理流程.png" alt="线程池的主要处理流程" style="zoom:80%;" />

- 1、在创建了线程池后，开始等待请求。
- 2、当调用 execute（）方法添加一个请求任务时，线程池会做出如下判断
  - 2.1、如果正在运行的线程数量小于 corePoolsize，那么马上创建线程运行这个任务;
  - 2.2、如果正在运行的线程数量大于或等于 corePoolsize，那么将这个任务放入队列;
  - 2.3、如果这个时候队列满了且正在运行的线程数量还小于 maximumPoolsize，那么还是要创建非核心线程立刻运行这个任务;
  - 2.4、如果队列满了且正在运行的线程数量大于或等于 maximumPoolsize，那么线程池会启动饱和拒绝策略来执行。
- 3、当一个线程完成任务时，它会从队列中取下一个任务来执行
- 4、当一个线程无事可做超过一定的时间（ keepAliveTime）时，线程会判断
  - 如果当前运行的线程数大于 corePoolsize，那么这个线程就被停掉。
  - 所以线程池的所有任务完成后，它最终会收缩到 corePoolsizel的大小。

### 如何设置合理的参数

线程池的拒绝策略：

- **AbortPolicy**（默认）：直接抛出RejectedExecutionException异常阻止系统正常运行
- **CallerRunsPolicy**：“调用者运行”——种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
- **DiscardOldestPolicy**：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
- **DiscardPolicy**：该策略默默地丟弃无法处理的任务，不予仼何处理也不拋出异常。如果允许任务丟失，这是最好的一种策略。

-----

## java.util.function

<img src="https://github.com/jinyu52370/Java/blob/master/JavaSE/documents/images/java.util.function四大函数式接口.png" alt="java.util.function四大函数式接口" style="zoom: 60%;" />