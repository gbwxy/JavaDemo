package com.gbwxy.demo.entity;

public class Robot {
    static {
        System.out.println("Hello every body, I am Robot.");
    }

    private String name;

    public void sayHi(String helloSentence) {
        System.out.println(name + ":" + helloSentence);
    }

    private String sayHello(System tag) {
        return "hello :" + tag;
    }
}
