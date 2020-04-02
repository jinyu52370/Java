package com.jinyu.jvm;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/31 14:38
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 */
public class JVMAdjustParam {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();

        System.out.println("-Xmx:    maxMemory:\t" + maxMemory + " byte" + "\t=\t" + (maxMemory/1024/1024) + " Mb");
        System.out.println("-Xms:    totalMemory:\t" + totalMemory + " byte" + "\t=\t" + (totalMemory/1024/1024) + " Mb");


        /*
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */
//        byte[] bytes = new byte[1024 * 1024];
//        String str = "jjjjjjjjjjj";
//        while (true){
//            str += new Random().nextInt(888888888) + new Random().nextInt(999999999);
//        }
    }
}
