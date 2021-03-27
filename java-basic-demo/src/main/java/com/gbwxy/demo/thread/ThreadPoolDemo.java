package com.gbwxy.demo.thread;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/4/16
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Future<String> future1 = executor.submit(new MyCallable());
//        Future<String> future2 = executor.submit(new MyCallable());
//        Future<String> future3 = executor.submit(new MyCallable());
//        Future<String> future4 = executor.submit(new MyCallable());
//        Future<String> future5 = executor.submit(new MyCallable());
//        Future<String> future6 = executor.submit(new MyCallable());

        int corePoolSize = 10;
        int maxPoolSize = 100;
        long keepAliveTime = 10L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedTransferQueue<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit,
                workQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        Future<String> future1 = executor.submit(new MyCallable());
        Future<String> future2 = executor.submit(new MyCallable());


        if (!future1.isDone()) {
            System.out.println("please wait ...");
        }
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


//        CompletableFuture<String> completableFuture = executorService.submit(new MyCallable());
    }
}
