package study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/28
 */
@Slf4j
public class Zookeeper_SetData_API_ASync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    static ZooKeeper zooKeeper;
    static String path = "/zk_test";


    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new Zookeeper_SetData_API_ASync_Usage());
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        zooKeeper.create(path,
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        byte[] data = zooKeeper.getData(path, true, null);
        log.info("=============  Get data :" + new String(data));
        zooKeeper.setData(path, "HelloWorld.".getBytes(), -1, new ISetCallback(), "Set data first.");

        zooKeeper.setData(path, "HelloWorld.".getBytes(), -1, new ISetCallback(), "Set data secand.");

        zooKeeper.delete(path, -1);

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


    static class ISetCallback implements AsyncCallback.StatCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            log.info("============== ctx :" + ctx + " rc:" + rc + ", path:" + path);
            log.info("==============  Czxid:" + stat.getCzxid() + ", Mzxid:" + stat.getMzxid() +
                    ", version:" + stat.getVersion());
            if (rc == 0) {
                log.info("============== SetCallback SUCCESS. ");
            }

        }
    }

}
