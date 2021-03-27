package com.gbwxy.demo.thread;

/* 创建一个callback接口
 * 用于实现java回调，请仔细阅读下面的注释
 * */
public class CallBackImp implements ICallBack {
    private String name;

    public CallBackImp(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runnable start : " + name + ",i = " + i);
        }
    }
}

