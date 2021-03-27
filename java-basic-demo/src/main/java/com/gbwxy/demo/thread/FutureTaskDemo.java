package com.gbwxy.demo.thread;

import java.util.concurrent.FutureTask;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        Thread t1 = new Thread(futureTask);
        t1.start();
        if (!futureTask.isDone()) {
            System.out.println("Please wait ...");
        }
        System.out.println("Done!");
    }
}
