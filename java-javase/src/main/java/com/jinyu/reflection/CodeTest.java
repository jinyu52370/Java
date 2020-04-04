package com.jinyu.reflection;

import com.jinyu.entity.User;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @description
 * @date 2020/3/27 11:46
 */
public class CodeTest {
    @Test
    public void classTest(){
        User user = new User();

        /*
         * 创建Class实例的方法
         *  1. 通过 类名.class 创建指定类的实例
         *  2. 通过 对象.getClass() 获取对应是实例对象的类的实例
         *  3. （常用）已知一个类的全类名，且在该类的路径下，通过Class类的静态方法 forName() 获取，可能抛出ClassNotFoundException
         *  4. ClassLoader
         */
        try {
            Class clazz1 = User.class;

            Class clazz2 = user.getClass();

            Class clazz3 = Class.forName("com.jjj.entity.ReflectUser");

            ClassLoader cl = this.getClass().getClassLoader();
            Class clazz4 = cl.loadClass("com.jjj.entity.ReflectUser");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTest(){
        try {
            Class<?> clazz = Class.forName("com.jjj.entity.ReflectUser");

            System.out.println("父类：" + clazz.getSuperclass().getName());

            System.out.println("\n接口：");
            Arrays.stream(clazz.getInterfaces()).map(Class::getName).forEach(System.out::println);

            System.out.println("\n构造名：");
            Arrays.stream(clazz.getConstructors()).map(Constructor::getName).forEach(System.out::println);

            System.out.println("\n构造修饰符：");
            Arrays.stream(clazz.getConstructors()).map(Constructor::getModifiers).forEach(System.out::println);

            System.out.println("\n构造参数数据类型：");
            List<Class<?>[]> parameterTypesList = Arrays.stream(clazz.getConstructors()).map(Constructor::getParameterTypes).collect(Collectors.toList());
            for (Class<?>[] parameterTypes : parameterTypesList){
                Arrays.stream(parameterTypes).map(Class::getName).forEach(System.out::println);
            }

            /*
             * 1：public
             * 2：private
             */
            System.out.println("\nDeclared构造名：");
            Arrays.stream(clazz.getDeclaredConstructors()).map(Constructor::getName).forEach(System.out::println);

            System.out.println("\nDeclared构造修饰符：");
            Arrays.stream(clazz.getDeclaredConstructors()).map(Constructor::getModifiers).forEach(System.out::println);

            System.out.println("\nDeclared构造参数数据类型：");
            List<Class<?>[]> parameterTypesList1 = Arrays.stream(clazz.getDeclaredConstructors()).map(Constructor::getParameterTypes).collect(Collectors.toList());
            for (Class<?>[] parameterTypes : parameterTypesList1){
                Arrays.stream(parameterTypes).map(Class::getName).forEach(System.out::println);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void instanceTest(){
        try {
            Class<?> clazz = Class.forName("com.jjj.entity.ReflectUser");

            /*
             * 公有无参构造
             * 公有有参构造
             * 私有构造
             */
//            ReflectUser user = (ReflectUser)clazz.newInstance();

//            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
//            ReflectUser user = (ReflectUser) constructor.newInstance("jjj", 222);
            Constructor<?> constructor = clazz.getDeclaredConstructor(int.class, String.class, boolean.class);
            constructor.setAccessible(true);
            User user = (User) constructor.newInstance(1, "asdasd", true);

            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void methodTest(){
        try {
            Class<?> clazz = Class.forName("com.jjj.entity.ReflectUser");

            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods){
                System.out.println("方法名：" + method.getName());
                System.out.println("返回值类型：" + method.getReturnType());
                System.out.println("修饰符：" + method.getModifiers());

                if (method.getParameterTypes().length != 0) {
                    System.out.print("方法参数类型：");
                    Arrays.stream(method.getParameterTypes()).map(Class::getName).forEach(System.out::println);
                }
                System.out.println("=======================\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fieldTest(){
        try {
            Class<?> clazz = Class.forName("com.jjj.entity.ReflectUser");
            User user = (User) clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                System.out.println("修饰符" + field.getModifiers());
                System.out.println("属性类型" + field.getType());
                System.out.println("属性名" + field.getName());
                System.out.println("=======================\n");
            }

//            System.out.println(clazz.getPackage());

            Field age = clazz.getDeclaredField("age");
            age.setAccessible(true);
            age.set(user, 108);
            System.out.println(age.get(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invokeTest(){
        try {
            Class<?> clazz = Class.forName("com.jjj.entity.ReflectUser");
            Constructor<?> constructor = clazz.getConstructor();
            User user = (User) constructor.newInstance();

            Method method = clazz.getDeclaredMethod("privateMethod", String.class);
            method.setAccessible(true);
            method.invoke(user, "hhh");

            Method method1 = clazz.getDeclaredMethod("privateMethod", String.class, int.class);
            method1.setAccessible(true);
            method1.invoke(user, "qwer", 1);

            user.setName("牛啤酒");
            Method getName = clazz.getMethod("getName");
            System.out.println(getName.invoke(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
