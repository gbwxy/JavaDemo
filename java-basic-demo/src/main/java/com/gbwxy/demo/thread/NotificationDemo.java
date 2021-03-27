package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class NotificationDemo {

    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {

        final NotificationDemo test = new NotificationDemo();
        Runnable waitTask = new Runnable() {
            @Override
            public void run() {
                try {
                    test.shouldGo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished.");
            }
        };
        Runnable notifyTask = new Runnable() {
            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread().getName() + " finished.");
            }
        };


        Thread thread1 = new Thread(waitTask, "WT1");
        Thread thread2 = new Thread(waitTask, "WT2");
        Thread thread3 = new Thread(waitTask, "WT3");
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.currentThread().sleep(100);
        Thread thread4 = new Thread(notifyTask, "NT1");
        thread4.start();
    }


    private synchronized void shouldGo() throws InterruptedException {
        while (!go) {
            System.out.println(Thread.currentThread()
                    + " is going to wait on this object.");
            wait();
            System.out.println(Thread.currentThread()
                    + " is woken up.");
        }
        go = false;
    }

    private synchronized void go() {
        while (!go) {
            System.out.println(Thread.currentThread()
                    + " is going to notify all or one thread waiting on thread.");
            go = true;
            //notify();
            notifyAll();
        }


    }
}
