package com.gbwxy.demo.jvm;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ReferenceQueueTest {
    private static ReferenceQueue<NormalObject> rq = new ReferenceQueue<>();

    private static void checkQueue() {
        Reference<NormalObject> ref = null;
        while ((ref = (Reference<NormalObject>) rq.poll()) != null) {
            System.out.println("In queue : " + ((NormalObjectWeakReference) (ref)).name);
            System.out.println("reference object : " + ref.get());
        }
    }

    public static void main(String[] args) {
        ArrayList<WeakReference<NormalObject>> weakList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ReferenceQueue<NormalObject> rq = ReferenceQueueTest.rq;
            weakList.add(new NormalObjectWeakReference(new NormalObject("weak name " + i), rq));
            System.out.println("Create weak:" + weakList.get(i));
        }
        System.out.println("first check.");
        checkQueue();
        System.gc();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("second check.");
        checkQueue();
    }
}
