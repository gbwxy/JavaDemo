package com.gbwxy.demo.thread;

import java.util.concurrent.Callable;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        String value = "test";
        System.out.println("Ready to Work.");
        Thread.currentThread().sleep(5000);
        System.out.println("task done.");
        return value;
    }
}
