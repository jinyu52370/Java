package com.jinyu.innerclass;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/3/18 11:57
 */
public class InnerClass {
    int i;
    public String j;
    private int z;

    class A{
        int i;

        public void setI(){
            this.i = 10;
        }

        public void setInnerClassFields(){
            InnerClass.this.i = 1;
            InnerClass.this.j = "j";
            InnerClass.this.z = 1;
        }
    }

    public void setFields(){
        new A().setInnerClassFields();
    }

    public void getFields(){
        System.out.println(this.i);
        System.out.println(this.j);
        System.out.println(this.z);
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        innerClass.setFields();
        innerClass.getFields();
    }
}
