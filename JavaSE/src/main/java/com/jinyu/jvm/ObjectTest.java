package com.jinyu.jvm;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/30 12:30
 */
public class ObjectTest {
    public static void main(String[] args) {
        /*
         * Object：Bootstrap ClassLoader
         */
        try {
            Object o = new Object();
            //null
            System.out.println(o.getClass().getClassLoader());
            //NullPointerException
            System.out.println(o.getClass().getClassLoader().getParent());
            //NullPointerException
            System.out.println(o.getClass().getClassLoader().getParent().getParent());
        } catch (NullPointerException e) {}

        System.out.println("\n=============================\n");

        /*
         * ObjectTest：AppClassLoader
         */
        ObjectTest objectTest = new ObjectTest();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(objectTest.getClass().getClassLoader());
        //sun.misc.Launcher$ExtClassLoader@677327b6
        System.out.println(objectTest.getClass().getClassLoader().getParent());
        //null
        System.out.println(objectTest.getClass().getClassLoader().getParent().getParent());
    }
}