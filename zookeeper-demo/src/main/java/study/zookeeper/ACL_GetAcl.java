package study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
public class ACL_GetAcl {

    private static ZooKeeper zoo;
    final static CountDownLatch connectedSignal = new CountDownLatch(1);

    public static ZooKeeper connect(String host) throws IOException, InterruptedException {
        zoo = new ZooKeeper(host, 5000, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState() == KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    public void close() throws InterruptedException {
        zoo.close();
    }

    public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zoo.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final String path = "/zookeeper";
        final ZooKeeper connect = connect("10.0.45.193:2181");

        List<ACL> acls = connect.getACL(path, connect.exists(path, false));
        for (ACL acl : acls) {
            System.out.println(acl.getPerms());
            System.out.println(acl.getId());
        }
    }

}
