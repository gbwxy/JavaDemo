package study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/28
 */
@Slf4j
public class Zookeeper_GetChildren_API_ASync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    static ZooKeeper zooKeeper;
    static String path = "/zk_test";

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_GetChildren_API_ASync_Usage());
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        zooKeeper.create(path,
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);


        zooKeeper.create(path + "/c1",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);


        zooKeeper.getChildren(path, true, new IChildren2Callback(), null);


        zooKeeper.create(path + "/c2",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        /**
         * 删除数据节点之前需要先删除其child
         */
        zooKeeper.delete(path + "/c1", 0);
        zooKeeper.delete(path + "/c2", 0);
        zooKeeper.delete(path, 0);

        Thread.sleep(Integer.MAX_VALUE);


    }


    @Override
    public void process(WatchedEvent event) {
        log.info("=============  Receive watched event. event  :" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    log.info("==============  ReGet Child :" + zooKeeper.getChildren(event.getPath(), true));
                } catch (Exception ex) {
                    log.info("==============  ReGet Child error :" + ex);
                }
            }

        }
    }


    static class IChildren2Callback implements AsyncCallback.Children2Callback {

        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
            log.info("==============  Get Children znode result: [ response  code: " + rc + ", param path :" + path
                    + ", ctx: " + ctx + ", children list :" + children + ", stat :" + stat);

        }
    }

}
