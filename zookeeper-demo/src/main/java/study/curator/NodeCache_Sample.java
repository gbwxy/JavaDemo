package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
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
public class NodeCache_Sample {

    static String path = "/zk-book/nodecache";
    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);

    static CountDownLatch semaphore = new CountDownLatch(1);
    static ExecutorService tp = Executors.newFixedThreadPool(2);


    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .connectionTimeoutMs(500000)
            .retryPolicy(retryPolicy)
            .build();


    public static void main(String[] args) throws Exception {
        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "HelloWorld.".getBytes());


        final NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);
        cache.getListenable()
                .addListener(new NodeCacheListener() {
                    @Override
                    public void nodeChanged() throws Exception {
                        log.info("================  Node data update, new data :" + new String(cache.getCurrentData().getData()));
                    }
                });


        client.setData()
                .forPath(path, "================  ChangeData first time. over.".getBytes());
        Thread.sleep(100);
        client.setData()
                .forPath(path, "================  ChangeData second time. over.".getBytes());

        Thread.sleep(1000);
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath(path);
        Thread.sleep(Integer.MAX_VALUE);

    }


}
