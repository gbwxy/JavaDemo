package demo;

import org.springframework.data.redis.core.RedisTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/12/2
 */
public class YuxiRedis {
    static RedisTemplate redisTemplate;

    public static String getVehicleCode(String name) {
        SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat sfd = new SimpleDateFormat("yyMMddHHmm");

        String strNow = sfd.format(new Date());

        String vehicleCacheKey = "vehicle:code" + strNow;
        long tmpCode;

        if (redisTemplate.hasKey(vehicleCacheKey)) {
            tmpCode = redisTemplate.opsForValue().increment(vehicleCacheKey);
        } else {
            tmpCode = redisTemplate.opsForValue().increment(vehicleCacheKey);
            redisTemplate.expire(vehicleCacheKey, 2 * 60, TimeUnit.MINUTES);
        }

        DecimalFormat df = new DecimalFormat("0000");
        String code = df.format(tmpCode);


        return strNow + code;
    }

    public void test() throws Exception {

        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 100; i++) {
            MyTask task = new MyTask("name" + i);
            executor.execute(task);
        }

        System.in.read(); //阻塞主线程


    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println("name = " + name);
                getVehicleCode(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
