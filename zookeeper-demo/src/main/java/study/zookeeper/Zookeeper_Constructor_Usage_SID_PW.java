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
public class Zookeeper_Constructor_Usage_SID_PW {

    public static void main(String[] args) throws Exception {

        CountDownLatch connectedSemaphore = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new MyWatcher(connectedSemaphore));

        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

        //zooKeeper.close();

        ZooKeeper zooKeeper2 = new ZooKeeper("10.0.45.193:2181",
                5000,
                new MyWatcher(connectedSemaphore),
                1L,
                "test".getBytes());
        log.info("=============  zooKeeper2 state is :" + zooKeeper.getState());


        ZooKeeper zooKeeper3 = new ZooKeeper("10.0.45.193:2181",
                5000,
                new MyWatcher(connectedSemaphore),
                sessionId,
                passwd);
        log.info("=============  zooKeeper3 state is :" + zooKeeper.getState());

        Thread.sleep(Integer.MAX_VALUE);
    }


    static class MyWatcher implements Watcher {
        private CountDownLatch connectedSemaphore;

        public MyWatcher(CountDownLatch countDownLatch) {
            connectedSemaphore = countDownLatch;
        }

        @Override
        public void process(WatchedEvent event) {
            log.info("=============  Receive watched event. event  :" + event);
            if (event.getState() == Event.KeeperState.SyncConnected) {
                connectedSemaphore.countDown();
            }
        }
    }


}
