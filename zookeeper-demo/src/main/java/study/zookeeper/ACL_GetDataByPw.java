package study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
public class ACL_GetDataByPw {
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
        final String path = "/zookeeper/setAcl";
        final ZooKeeper connect = connect("10.0.45.193:2181");

        /**
         *   会话添加用户和密码信息
         *   如果不增加这行代码，报错  NoAuthException: KeeperErrorCode = NoAuth for /zookeeper/setAcl
         */
        //connect.addAuthInfo("digest", "user:123456".getBytes());

        byte[] data = connect.getData(path, false, null);
        System.out.println(new String(data, "UTF-8"));
    }
}
