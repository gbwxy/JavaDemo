package study.curator.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/25
 */
@Slf4j
public class Zkpaths_Sample {
    static String path = "/curator_zkpath_sample";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
        log.info("================= " + ZKPaths.fixForNamespace(path, "sub"));
        log.info("================= " + ZKPaths.makePath(path, "sub"));
        log.info("================= " + ZKPaths.getNodeFromPath("/curator_zkpath_sample/sub1"));


        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode("/curator_zkpath_sample/sub1");
        log.info("================= " + pn.getPath());
        log.info("================= " + pn.getNode());


        String dir1 = path + "/child1";
        String dir2 = path + "/child2";


        ZKPaths.mkdirs(zooKeeper, dir1);
        ZKPaths.mkdirs(zooKeeper, dir2);

//        Thread.sleep(Integer.MAX_VALUE);

        log.info("================= " + ZKPaths.getSortedChildren(zooKeeper, path));
        ZKPaths.deleteChildren(client.getZookeeperClient().getZooKeeper(), path, true);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
