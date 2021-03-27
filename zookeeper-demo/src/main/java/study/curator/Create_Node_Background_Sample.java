package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/18
 */
@Slf4j
public class Create_Node_Background_Sample {

    static String path = "/zk-book";
    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);

    static CountDownLatch semaphore = new CountDownLatch(1);
    static ExecutorService tp = Executors.newFixedThreadPool(2);

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .connectionTimeoutMs(500000)
            .retryPolicy(retryPolicy)
            //.namespace("base")//独立命名空间，该client的所有操作都在这个数据节点上进行
            .build();


    public static void main(String[] args) throws Exception {
        client.start();

        log.info("=================== Main thread: " + Thread.currentThread().getName());
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new MyBackCallBack(), tp)
                .forPath(path, "Hello World.".getBytes());

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        log.info("=================== BackgroundCallback Thread of processResult :" + Thread.currentThread().getName());
                        log.info("=================== BackgroundCallback event [ code :" + event.getResultCode() + ", type : " + event.getType() + "]");
                        semaphore.countDown();
                    }
                })
                .forPath(path + "C1", "init".getBytes());


        semaphore.await();
        tp.shutdown();

        Thread.sleep(Integer.MAX_VALUE);
    }


    static class MyBackCallBack implements BackgroundCallback {

        @Override
        public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            log.info("=================== MyBackCallBack Thread of processResult:" + Thread.currentThread().getName());
            log.info("=================== MyBackCallBack event [ code :" + event.getResultCode() + ", type : " + event.getType() + "]");
            semaphore.countDown();

        }
    }
}
