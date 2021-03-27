package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class WaitSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread A is waiting to get lock.");
                synchronized (lock){
                    System.out.println("Thread A get lock.");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread A do wait method.");
                    try {
                        //lock.wait(1000);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread A done !");
                }
            }
        }).start();

        Thread.currentThread().sleep(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread B is waiting to get lock.");
                synchronized (lock){
                    System.out.println("Thread B get lock.");
                    try {
                        System.out.println("Thread B sleep 200ms.");
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread B notify lock.");
                    lock.notify();
                    System.out.println("Thread B done !");
                }
            }
        }).start();
    }
}
