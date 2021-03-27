package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class ThreadDemo {
//    public static void main(String[] args) {
//        MyThread thread1 = new MyThread("Thread1");
//        MyThread thread2 = new MyThread("thread2");
//        MyThread thread3 = new MyThread("thread3");
//        MyThread thread4 = new MyThread("thread4");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//
//    }


/* //主线程等待法
   public static void main(String[] args) throws InterruptedException {
        MainThreadCycleWait cycleWait = new MainThreadCycleWait();
        Thread t1 = new Thread(cycleWait);
        t1.start();
        while (cycleWait.getValue() == null) {
            System.out.println("wait ...");
            Thread.currentThread().sleep(100);
        }
        System.out.println(cycleWait.getValue());
    }*/

    //使用Thread类的join()阻塞当前线程以等待子线程完成
    public static void main(String[] args) throws InterruptedException {
        MainThreadCycleWait cycleWait = new MainThreadCycleWait();
        Thread t1 = new Thread(cycleWait);
        t1.start();
        t1.join();
        System.out.println(cycleWait.getValue());
    }
}
