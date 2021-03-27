package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable interruptTask = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(100);
                        i++;
                        System.out.println(Thread.currentThread().getName() + " state is " + Thread.currentThread().getState() + " loop " + i);
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " state is " + Thread.currentThread().getState());
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(interruptTask, "t1");
        System.out.println(thread1.getName() + " state is (" + thread1.getState() + ")");
        thread1.start();
        System.out.println(thread1.getName() + " state is (" + thread1.getState() + ")");
        Thread.currentThread().sleep(500);
        thread1.interrupt();
        System.out.println(thread1.getName() + " state is (" + thread1.getState() + ")");
        Thread.currentThread().sleep(200);
        System.out.println(thread1.getName() + " state is (" + thread1.getState() + ")");
    }
}
