package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/14
 */
public class DeadLock {

    private static Object a = new Object();
    private static Object b = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Curerrent Thread is " + Thread.currentThread().getName());
        System.out.println("begin a.");
        threada.start();
        Thread.currentThread().sleep(500);
        System.out.println("begin b.");
        threadb.start();
    }


    static Thread threada = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (a) {
                System.out.println(" threada a");
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(" threada b");
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    static Thread threadb = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (b) {
                System.out.println(" threadb b");
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(" threadb a");
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });


}
