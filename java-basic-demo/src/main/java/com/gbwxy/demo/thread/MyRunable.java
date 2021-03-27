package com.gbwxy.demo.thread;

import lombok.Data;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
@Data
public class MyRunable implements Runnable {
    private String name;

    public MyRunable(String name) {
        this.name = name;
    }

    public MyRunable() {

    }

    public void MyRunable(ICallBack callBack) {

    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //System.out.println("Current Thread is " + Thread.currentThread().getName());
            System.out.println("Runnable start : " + this.name + ",i = " + i);
        }
    }
}
