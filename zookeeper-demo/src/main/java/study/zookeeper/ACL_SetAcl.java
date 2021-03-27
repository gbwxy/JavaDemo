package study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
public class ACL_SetAcl {

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

        // 创建ACL
        ACL acl = new ACL();
        // 创建Id，也可以设置构造方法传入scheme和id
        Id id = new Id("digest", "user:6DY5WhzOfGsWQ1XFuIyzxkpwdPo=");
        acl.setId(id);
        acl.setPerms(Perms.ALL);

        List<ACL> acls = new ArrayList<>();
        acls.add(acl);

        // 修改ACL
        Stat setACL = connect.setACL(path, acls, connect.exists(path, false).getAversion());

        // 获取Acl
        System.out.println(connect.getACL(path, setACL));
    }

}
