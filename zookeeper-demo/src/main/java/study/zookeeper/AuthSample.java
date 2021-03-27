package study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/17
 */
public class AuthSample {
    static String path = "/zk-auth-test";

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        zooKeeper.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
