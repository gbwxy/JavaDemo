package study.zookeeper.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/10
 */
@Slf4j
public class MyWatcher implements Watcher {

    ZooKeeper zk;
    CountDownLatch countDownLatch;

    public MyWatcher(CountDownLatch countDownLatch) throws Exception {
        this.countDownLatch = countDownLatch;
        this.zk = new ZooKeeper("10.0.45.193:2181",
                5000,
                null);
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            Event.KeeperState state = event.getState();
            Event.EventType type = event.getType();
            switch (state) {
                case SyncConnected:
                    switch (type) {
                        case None:
                            countDownLatch.countDown();
                            log.info("=============  Connected.");
                            break;
                        case NodeCreated:
                            log.info("=============  Node [" + event.getPath() + "] created.");
                            //zk.exists(event.getPath() + "/c1", true);
                            break;
                        case NodeDeleted:
                            log.info("=============  Node [" + event.getPath() + "] Deleted.");
                            //zk.exists(event.getPath() + "/c1", true);
                            break;
                        case NodeDataChanged:
                            log.info("=============  Node [" + event.getPath() + "] DataChanged.");
                            //zk.exists(event.getPath(), true);
                            break;
                        case NodeChildrenChanged:
                            log.info("=============  Node [" + event.getPath() + "] ChildrenChanged.");
                            //zk.exists(event.getPath(), true);
                            break;
                        default:
                            break;
                    }
                    break;
                case Expired:
                    break;
                case AuthFailed:
                    break;
                case Disconnected:
                    break;
                case ConnectedReadOnly:
                    break;
                case SaslAuthenticated:
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            log.info("============  Error:" + ex);
        }
    }
}
