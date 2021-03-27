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
public class Zookeeper_GetData_API_ASync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    static ZooKeeper zooKeeper;
    static String path = "/zk-book";
    static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_GetData_API_ASync_Usage());
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        zooKeeper.create(path,
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        log.info("=============  Get Data first.");
        zooKeeper.getData(path, true, new IDataCallback(), "Get Data first.");
        log.info("=============  Set Data.");
        zooKeeper.setData(path, "HelloWorld.".getBytes(), stat.getVersion());
        log.info("=============  Get Data Secand.");
        zooKeeper.getData(path, true, new IDataCallback(), "Get Data Secand.");

        Thread.sleep(Integer.MAX_VALUE);


    }


    @Override
    public void process(WatchedEvent event) {
        log.info("=============  Receive watched event. event  :" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            if (event.getType() == Event.EventType.None && event.getPath() == null) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                zooKeeper.getData(event.getPath(), true, new IDataCallback(), "NodeDataChanged");
            }
        }
    }


    static class IDataCallback implements AsyncCallback.DataCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            log.info("============== ctx :" + ctx + " rc:" + rc + ", path:" + path + ", data:" + new String(data));
            log.info("==============  Czxid:" + stat.getCzxid() + ", Mzxid:" + stat.getMzxid() +
                    ", version:" + stat.getVersion());
        }
    }
}
