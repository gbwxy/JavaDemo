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
public class AuthSample_Get {
    static String path = "/zk-auth-test";

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper1 = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper1.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper2 = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        zooKeeper2.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper2.getData(path,false,null);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
