package study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
public class AuthSample_Super {
    static String path = "/zk-book-auth-test";

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper1 = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper1.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper2 = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        zooKeeper2.addAuthInfo("digest", "foo:zk-book".getBytes());
        System.out.println(zooKeeper2.getData(path, false, null));


        ZooKeeper zooKeeper3 = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        zooKeeper3.addAuthInfo("digest", "foo:false".getBytes());
        System.out.println(zooKeeper3.getData(path, false, null));

        Thread.sleep(Integer.MAX_VALUE);
    }
}
