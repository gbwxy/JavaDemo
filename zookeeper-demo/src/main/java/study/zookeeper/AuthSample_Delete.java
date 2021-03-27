package study.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/17
 */
@Slf4j
public class AuthSample_Delete {
    static String path = "/zk-book-auth-test";
    static String path2 = "/zk-book-auth-test/child";

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper1 = new ZooKeeper("10.0.45.193:2181",
                5000, null);

        ZooKeeper zooKeeper4 = new ZooKeeper("10.0.45.193:2181",
                5000, null);
        zooKeeper4.delete(path, -1);
        log.info("=====================  delete path :" + path);

        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());


        //设置多个acl
        List<ACL> acls = new ArrayList<>();
        acls.addAll(ZooDefs.Ids.CREATOR_ALL_ACL);
        acls.addAll(ZooDefs.Ids.OPEN_ACL_UNSAFE);

        zooKeeper1.create(path, "init".getBytes(), acls, CreateMode.PERSISTENT);
        zooKeeper1.create(path2, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        //当client对一个数据节点加了权限信息后，对于删除而言，其作用是其子节点，所以依然可以自由的删除节点本身，前提是改节点没有子节点
        ZooKeeper zooKeeper3 = new ZooKeeper("10.0.45.193:2181",
                5000, null);
        zooKeeper3.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper3.delete(path2, -1);
        log.info("=====================  delete path :" + path2);


        //当path下有内容的时候不允许删除
        ZooKeeper zooKeeper2 = new ZooKeeper("10.0.45.193:2181",
                5000, null);
        zooKeeper2.delete(path, -1);
        log.info("=====================  delete path :" + path);


        Thread.sleep(Integer.MAX_VALUE);
    }
}
