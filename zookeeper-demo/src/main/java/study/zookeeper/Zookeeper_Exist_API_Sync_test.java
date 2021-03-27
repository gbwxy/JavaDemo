package study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/29
 */
@Slf4j
public class Zookeeper_Exist_API_Sync_test implements Watcher {

    static CountDownLatch connectSemaphore = new CountDownLatch(1);
    static ZooKeeper zk;
    static String path = "/zk-book";

    /**
     * 1.通过exists 接口来检测是否存在指定节点，同事注册一个 Watcher
     * 2.创建节点 /zk-book 此时服务端马上会向客户端发送一个事件通知：NodeCreated
     * 客户端在收到该事件通知后，再次调用 exists 接口，同时注册 Watcher
     * 3.更新该节点数据，此时服务端马上会向客户端发送一个事件通知：NodeDataChanged
     * 客户端在收到该事件通知后，再次调用 exists 接口，同时注册 Watcher
     * 4.创建子节点 /zk-book/c1
     * 5.删除子节点 /zk-book/c1
     * 6.删除节点/zk-book
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_Exist_API_Sync_test());
        log.info("=============  zookeeper state is :" + zk.getState());
        connectSemaphore.await();
        log.info("=============  zookeeper state is :" + zk.getState());


        //zk.exists(path, true);

        zk.exists(path + "/c1", true);

        zk.create(path,
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        zk.setData(path, "HelloWorld.".getBytes(), -1);

        zk.create(path + "/c1",
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);


        zk.delete(path + "/c1", -1);
        zk.delete(path, -1);


        Thread.sleep(Integer.MAX_VALUE);

    }


    /**
     * 1.无论指定节点是否存在，exists 接口都可以注册 Watcher
     * 2.exists 接口监听到一次事件之后，不再监听下一次事件，如果要监听需要重新注册 Watcher
     * 3.exists 接口中注册的 Watcher，能够对节点创建、节点删除、节点数据更新事假进行监听
     * 4.对指定节点的子节点的各种变化，都不会通知客户端
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {

        try {

            if (event.getState() == Event.KeeperState.SyncConnected) {
                if (event.getType() == Event.EventType.None && event.getPath() == null) {
                    connectSemaphore.countDown();
                } else if (event.getType() == Event.EventType.NodeCreated) {
                    log.info("=============  Node [" + event.getPath() + "] created.");
                    zk.exists(event.getPath() + "/c1", true);
                } else if (event.getType() == Event.EventType.NodeDeleted) {
                    log.info("=============  Node [" + event.getPath() + "] Deleted.");
                    zk.exists(event.getPath() + "/c1", true);
                } else if (event.getType() == Event.EventType.NodeDataChanged) {
                    log.info("=============  Node [" + event.getPath() + "] DataChanged.");
                    // zk.exists(event.getPath(), true);
                } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                    log.info("=============  Node [" + event.getPath() + "] ChildrenChanged.");
                    //zk.exists(event.getPath(), true);
                }
            }

        } catch (Exception ex) {
            log.info("============  Error:" + ex);
        }

    }
}
