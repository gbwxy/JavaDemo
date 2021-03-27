package com.gbwxy.demo.thread;

import lombok.Data;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
@Data
public class MainThreadCycleWait implements Runnable {
    private String value;

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = "We have data now.";
    }

}
