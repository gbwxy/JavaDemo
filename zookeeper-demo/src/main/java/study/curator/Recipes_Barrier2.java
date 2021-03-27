package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 描述：
 * <p>
 * DistributedDoubleBarrier  自动触发 Barrier 释放
 *
 * @Author wangjun
 * @Date 2020/9/22
 */
@Slf4j
public class Recipes_Barrier2 {
    static String barrier_path = "/curator_recipes_barrier_path";
    static DistributedDoubleBarrier barrier;


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.0.45.193:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        barrier = new DistributedDoubleBarrier(client, barrier_path, 5);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("========================== My Name is :" + Thread.currentThread().getName() + ". And I am a Barrier.");
                        Thread.sleep(Math.round(Math.random() * 3000));
//                        barrier.enter();
//                        log.info("========================== My Name is :" + Thread.currentThread().getName() + ". I am enter.");
                        Thread.sleep(Math.round(Math.random() * 3000));
                        barrier.leave();
                        log.info("========================== My Name is :" + Thread.currentThread().getName() + ". I am leave.");
                    } catch (Exception ex) {
                        log.error("======================  Error: " + ex.getMessage());
                    }
                }
            }).start();
        }

    }
}
