package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class ThreadRunStart {

    private static void attack() {
        System.out.println("Fight");
        System.out.println("Current Thread is " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                attack();
            }
        });
        System.out.println("current main thread is " + Thread.currentThread().getName());
        t.run();
        t.start();
    }
}
