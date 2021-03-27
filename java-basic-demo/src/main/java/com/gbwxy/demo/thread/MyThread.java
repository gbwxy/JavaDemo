package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //System.out.println("Current Thread is " + Thread.currentThread().getName());
            System.out.println("Thread start : " + this.name + ",i = " + i);
        }
    }
}
