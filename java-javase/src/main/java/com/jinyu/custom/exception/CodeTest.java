package com.jinyu.custom.exception;

import org.junit.Test;

class Age {
    private int age;

    public void testAge(int age) throws MyException {
        if (age >= 0 && age <= 150){
            this.age = age;
            System.out.println("年龄为：" + this.age);
        } else {
            throw new MyException("年龄不在0~150的范围内!");
        }
    }
}

/**
 * @author <a href="jinyu52370@outlook.com">JJJ</a>
 * @date 2020/4/2 20:28
 */
public class CodeTest {
    @Test
    public void exceptionTest(){
        Age age = new Age();
        try {
            age.testAge(1000);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
