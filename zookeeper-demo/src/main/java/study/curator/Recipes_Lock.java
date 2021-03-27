package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/22
 */
@Slf4j
public class Recipes_Lock {

    static String lock_path = "/character_root/lock";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();


    /**
     * Path must start with / character
     *
     * @param args
     */
    public static void main(String[] args) {
        client.start();
        /**
         *  Path must start with / character
         */
        final InterProcessMutex lock = new InterProcessMutex(client, lock_path + "/lock001");
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                        lock.acquire();
                        Thread.sleep(10);
                        lock.acquire();
//
//                        Thread.sleep(10);
//                        lock.acquire();
                    } catch (Exception ex) {
                        log.info("================  " + ex.getMessage());
                        ex.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    log.info("================  生成订单号：" + orderNo);
                    try {
                        Thread.sleep(1000);
                        lock.release();
                    } catch (Exception ex) {
                        log.info("================  error: " + ex.getMessage());
                        ex.printStackTrace();
                    }

                }
            }).start();

        }
        down.countDown();


        //client.close();
    }
}
