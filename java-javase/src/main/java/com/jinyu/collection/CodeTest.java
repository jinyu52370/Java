package com.jinyu.collection;

import com.jinyu.entity.ReflectUser;
import com.jinyu.enumpack.Season;
import org.junit.Test;

import java.util.*;

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 20:30
 */
public class CodeTest {
    /**
     * 自然排序
     */
    @Test
    public void treeSetTest(){
        TreeSet<Integer> treeSet1 = new TreeSet<>();

        treeSet1.add(5);
        treeSet1.add(4);
        treeSet1.add(3);
        System.out.println(treeSet1);

        System.out.println("\n===========================\n");

        TreeSet<ReflectUser> treeSet2 = new TreeSet<>();
        for (int i = 0;i < 3;i++) {
            treeSet2.add(new ReflectUser("user" + i, i));
        }
        treeSet2.add(new ReflectUser("jjj", -1));

        treeSet2.stream().map(ReflectUser::getAge).forEach(age -> System.out.print(age + " "));
    }

    @Test
    public void mapTest(){
        Map<String, Integer> map = new HashMap<>(16);

        for (int i=0;i<10;i++) {
            map.put("A" + i, i + 1);
        }

        map.keySet().forEach(key -> System.out.println("key: " + key + "\tvalue: " + map.get(key)));
    }

    @Test
    public void collectionsTest(){
        ArrayList<String> list = new ArrayList<>();

        list.add("1");
        list.add("ab");
        list.add("ccc");
        list.add("を");
        list.add("zxc");

        //倒序
//        Collections.reverse(list);

        //随机
//        Collections.shuffle(list);

        //自然排序
//        Collections.sort(list);

        //交换
//        Collections.swap(list, 1, 4);

        System.out.println(list);

        //定制排序
//        ArrayList<ReflectUser> users = new ArrayList<>();
//
//        users.add(new ReflectUser("jjj",20));
//        users.add(new ReflectUser("知识",123));
//        users.add(new ReflectUser("老师",12));
//        users.add(new ReflectUser("玩玩",9));
//
//        Collections.sort(users, Comparator.comparing(ReflectUser::getAge));
//
//        System.out.println(users);
    }
}
