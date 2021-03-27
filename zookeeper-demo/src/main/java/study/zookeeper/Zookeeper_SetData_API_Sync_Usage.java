package study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/28
 */
@Slf4j
public class Zookeeper_SetData_API_Sync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    static ZooKeeper zooKeeper;
    static String path = "/zk_test";


    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_SetData_API_Sync_Usage());
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        zooKeeper.create(path,
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        byte[] data = zooKeeper.getData(path, true, null);
        log.info("=============  Get data :" + new String(data));
        Stat stat = zooKeeper.setData(path, "HelloWorld.".getBytes(), -1);
        log.info("=============  Set data`s stat: czxId： " + stat.getCzxid() + ", MzxId:" + stat.getMzxid() + ", version:" + stat.getVersion());


        Stat stat2 = zooKeeper.setData(path, "HelloWorld.".getBytes(), -1);
        log.info("=============  Set data`s stat2: czxId： " + stat2.getCzxid() + ", MzxId:" + stat2.getMzxid() + ", version:" + stat2.getVersion());


        zooKeeper.delete(path, stat2.getVersion());

        Thread.sleep(Integer.MAX_VALUE);


    }


    @Override
    public void process(WatchedEvent event) {
        log.info("=============  Receive watched event. event  :" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                connectedSemaphore.countDown();
            }
        }
    }


}
