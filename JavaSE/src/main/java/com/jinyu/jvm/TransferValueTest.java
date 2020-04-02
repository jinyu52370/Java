package com.jinyu.jvm;

class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/31 12:33
 */
public class TransferValueTest {
    public void baseChangeValue(int age){
        age = 30;
    }

    public void referenceChangeValue(Person person){
        person.setName("xxx");
    }

    public void stringChangeValue(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TransferValueTest test = new TransferValueTest();

        /*
         * 基本类型传复印件
         *      main栈帧的age和baseChangeValue栈帧的age不是同一个，所以此时还是打印的main的age = 20
         */
        int age = 20;
        test.baseChangeValue(age);
        System.out.println("age=====\t" + age);

        /*
         * 引用类型传的是指针(地址)
         *      main栈帧的person指向堆中的Person实例"abc"，referenceChangeValue栈帧的person也指向"abc"，
         *      所以在referenceChangeValue方法中执行person.setName("xxx")后，此地址的Person实例就改变了，
         *      此时在main方法中打印person.getName()会得到"xxx"
         */
        Person person = new Person("abc");
        test.referenceChangeValue(person);
        System.out.println("personName=====\t" + person.getName());

        /*
         * 字符串类型
         *      main栈帧中的str指向字符串常量池中的"abc"，stringChangeValue栈帧的str也指向"abc"，
         *      stringChangeValue继续执行，字符串常量池中没有"xxx"，所以stringChangeValue的str指向"xxx"，
         *      此时main方法中打印str得到的还是"abc"
         */
        String str = "abc";
        test.stringChangeValue(str);
        System.out.println("str=====\t" + str);
    }
}
