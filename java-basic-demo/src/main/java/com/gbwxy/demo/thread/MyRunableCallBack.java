package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class MyRunableCallBack implements Runnable {
    private ICallBack callBack;

    public MyRunableCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void run() {
        callBack.run();
    }
}
