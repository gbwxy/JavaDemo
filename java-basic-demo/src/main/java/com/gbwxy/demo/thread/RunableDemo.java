package com.gbwxy.demo.thread;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class RunableDemo {
    public static void main(String[] args) throws InterruptedException {

        //Run方法传参
        //1.通过构造方法传递数据
        //2.通过变量和方法传递数据
        //3.通过回调函数传递数据

        //1.构造函数传参
        MyRunable runable1 = new MyRunable("Runable1");
        MyRunable runable2 = new MyRunable("runable2");
        //2.通过变量和方法传递数据
        MyRunable runable3 = new MyRunable();
        runable3.setName("runable3");

        Thread t1 = new Thread(runable1);
        Thread t2 = new Thread(runable2);
        Thread t3 = new Thread(runable3);

        //2.通过变量和方法传递数据
        String t4Name = "runable4";
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    //System.out.println("Current Thread is " + Thread.currentThread().getName());
                    System.out.println("Runnable start : " + t4Name + ",i = " + i);
                }
            }
        });

        String t5Name = "runable4";
        CallBackImp callBackImp = new CallBackImp(t5Name);
        MyRunableCallBack myRunableCallBack = new MyRunableCallBack(callBackImp);
        Thread t5 = new Thread(myRunableCallBack);


        t1.start();
        t1.join();
        t2.start();
        t3.start();
        t2.join();
        t4.start();
        t5.start();
    }
}
