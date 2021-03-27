package study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/28
 */
@Slf4j
public class Zookeeper_Constructor_Usage_Simple implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("10.0.45.193:2181", 5000, new Zookeeper_Constructor_Usage_Simple());

        log.info("=============  zookeeper state is :" + zooKeeper.getState());

        try {
            connectedSemaphore.await();
            log.info("=============  zookeeper state is :" + zooKeeper.getState());
        } catch (Exception ex) {
            log.info("=============  zookeeper session established.");
        }

    }


    @Override
    public void process(WatchedEvent event) {

        log.info("=============  Receive watched event. name is :" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedSemaphore.countDown();
        }
    }
}
