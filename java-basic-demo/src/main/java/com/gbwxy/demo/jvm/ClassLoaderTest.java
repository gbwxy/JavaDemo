package com.gbwxy.demo.jvm;

/**
 * @Author wangjun
 * @Date 2020/4/14
 */
public class ClassLoaderTest {

    private String name;
    private int age;

    //private Person robot = new Person();
    private final String comp = "Unicloud";
    private volatile static int compAge = 2;

    public ClassLoaderTest(String name, int age) {
        this.age = age;
        this.name = name;
    }

    static {
        System.out.println("first static code.");
    }

    static {
        System.out.println("second static code.");
    }


    private static Object a;
    private static Object b;


}




