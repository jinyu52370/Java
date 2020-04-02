package com.jinyu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/13 11:08
 */
@Data
@ToString
@EqualsAndHashCode
public class ReflectUser extends SkinColor implements Comparable, Serializable, School, Study {
    private static final long serialVersionUID = 1L;

    private String name;
    private static int sex;
    private int age;

    //静态代码块
    static {
        //只能使用static修饰的属性和方法
        sex = 1;
        System.out.println("静态代码块" + sex);
    }

    //非静态代码块
    {
        System.out.println("非静态代码块");
    }

    @Override
    public void school() {
        System.out.println("仙宫");
    }

    @Override
    public void study() {
        System.out.println("学习本科知识");
    }

    private void privateMethod(String info){
        System.out.println("这个是privateMethod：" + info);
    }

    private void privateMethod(String info, int i){
        System.out.println("这个是privateMethod的重载方法：" + info + i);
    }

    public void publicMethod(int info){
        System.out.println("这个是publicMethod：" + info);
    }

    public ReflectUser(){
        System.out.println("构造方法public ReflectUser()");
    }

    public ReflectUser(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("构造方法public ReflectUser(String name, int age)");
    }

    private ReflectUser(int age){
        this.age = age;
        System.out.println(this.age);
        System.out.println("构造方法private ReflectUser(int age)");
    }

    private ReflectUser(int age, String name, boolean sex){
        this.age = age;
        this.name = name;
        System.out.println("构造方法private ReflectUser(int age, String name, Boolean sex)");
    }

    @Override
    public int compareTo(Object o) {
        return this.age - ((ReflectUser) o).age;
    }

}
