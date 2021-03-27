package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 描述：
 * <p>
 * DistributedBarrier  手动释放 Barrier
 *
 * @Author wangjun
 * @Date 2020/9/22
 */
@Slf4j
public class Recipes_Barrier {
    static String barrier_path = "/curator_recipes_barrier_path";
    static DistributedBarrier barrier;


    /**
     * 通过  DistributedBarrier.setBarrier() 方法来完成 Barrier 的设置
     * 通过  DistributedBarrier.waitOnBarrier() 方法来等待 Barrier 的释放
     * 通过  DistributedBarrier.removeBarrier() 方法来释放 Barrier 同时触发所有等待该 Barrier 的线程同时进行各自的业务逻辑
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.0.45.193:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        barrier = new DistributedBarrier(client, barrier_path);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        barrier.setBarrier();
                        log.info("========================== My Name is :" + Thread.currentThread().getName() + ". And I am a Barrier.");
                        barrier.waitOnBarrier();
                        log.info("========================== My Name is :" + Thread.currentThread().getName() + ". I am Running.");
                    } catch (Exception ex) {
                        log.error("======================  Error: " + ex.getMessage());
                    }
                }
            }).start();
        }

        Thread.sleep(2000);
        barrier.removeBarrier();
    }
}
