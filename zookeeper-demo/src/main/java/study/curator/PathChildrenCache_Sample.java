package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
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
public class PathChildrenCache_Sample {
    static String path = "/zk-book";
    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);

    static CountDownLatch semaphore = new CountDownLatch(1);
    static ExecutorService tp = Executors.newFixedThreadPool(2);


    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .connectionTimeoutMs(500000)
            .retryPolicy(retryPolicy)
            .sessionTimeoutMs(500000)
            .build();


    public static void main(String[] args) throws Exception {
        client.start();


        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable()
                .addListener(new PathChildrenCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                        switch (event.getType()) {
                            case INITIALIZED:
                            case CONNECTION_LOST:
                            case CONNECTION_RECONNECTED:
                            case CONNECTION_SUSPENDED:
                                break;
                            case CHILD_REMOVED:
                                log.info("================    CHILD_REMOVED: " + event.getData().getPath());
                                break;
                            case CHILD_ADDED:
                                log.info("================    CHILD_ADDED: " + event.getData().getPath());
                                break;
                            case CHILD_UPDATED:
                                log.info("================    CHILD_UPDATED: " + event.getData().getPath());
                                break;
                        }
                    }
                });

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path);
        Thread.sleep(1000);

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path + "/c1");
        Thread.sleep(1000);

        client.setData()
                .forPath(path, "================  ChangeData first time. over.".getBytes());
        Thread.sleep(100);
        client.setData()
                .forPath(path, "================  ChangeData second time. over.".getBytes());

        client.delete()
                .deletingChildrenIfNeeded()
                .forPath(path + "/c1");

        Thread.sleep(1000);
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath(path);
        Thread.sleep(Integer.MAX_VALUE);

    }
}
