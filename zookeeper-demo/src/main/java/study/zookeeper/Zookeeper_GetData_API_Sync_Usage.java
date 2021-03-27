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
public class Zookeeper_GetData_API_Sync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    static ZooKeeper zooKeeper;
    static String path = "/zk-book";
    static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_GetData_API_Sync_Usage());
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        zooKeeper.create(path,
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        log.info("=============  Get data :" + new String(zooKeeper.getData(path, true, stat)));
        log.info("=============  Get data`s stat: czxId： " + stat.getCzxid() + ", MzxId:" + stat.getMzxid() + ", version:" + stat.getVersion());


        List<String> children = zooKeeper.getChildren(path, true);
        for (String child : children) {
            log.info("=============  child :" + child);
        }


        zooKeeper.setData(path, "HelloWorld.".getBytes(), stat.getVersion());

        zooKeeper.delete(path, stat.getVersion() + 1);

        Thread.sleep(Integer.MAX_VALUE);


    }


    @Override
    public void process(WatchedEvent event) {
        log.info("=============  Receive watched event. event  :" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {

                try {
                    log.info("=============  Get data :" + new String(zooKeeper.getData(path, true, stat)));
                    log.info("============= process Get data`s stat: czxId： " + stat.getCzxid() + ", MzxId:" + stat.getMzxid() + ", version:" + stat.getVersion());

                } catch (Exception ex) {

                }
            }

        }
    }


}
