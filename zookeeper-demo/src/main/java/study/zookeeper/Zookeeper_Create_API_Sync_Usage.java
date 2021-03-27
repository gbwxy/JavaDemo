package study.zookeeper;


import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/28
 */
@Slf4j
public class Zookeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_Create_API_Sync_Usage());
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        String path = "/zk-test";
        path = zooKeeper.create(path,
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        log.info("=============  同步创建完成，Path :" + path);

        log.info("=============  同步创建，Path :" + path);
        String path1 = zooKeeper.create(path + "/sync",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        log.info("=============  同步创建完成，Path :" + path1);


        log.info("=============  异步创建，Path :" + path);
        zooKeeper.create(path + "/async",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallBack(),
                "I am context.");


        zooKeeper.create(path + "/async",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallBack(),
                "I am context.");


        zooKeeper.create(path + "/async2",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallBack(),
                "I am context.");

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        log.info("=============  Receive watched event. event  :" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedSemaphore.countDown();
        }
    }

    static class IStringCallBack implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            log.info("=============  Create path result :[" + rc + "," + path + "," + "," + ctx + "]");

        }
    }
}
