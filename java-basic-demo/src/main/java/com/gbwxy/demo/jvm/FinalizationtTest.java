package com.gbwxy.demo.jvm;

public class FinalizationtTest {

    public static FinalizationtTest finalizationt;

    @Override
    protected void finalize() {
        System.out.println("Finalized.");
        finalizationt = this;
    }

    public static void main(String[] args) {
        FinalizationtTest f = new FinalizationtTest();
        System.out.println("First print:" + f);
        f = null;
        System.gc();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Second print:" + f);
        System.out.println(f.finalizationt);

    }
}
