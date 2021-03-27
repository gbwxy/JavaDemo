package study.zookeeper;


import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import study.zookeeper.watcher.MyWatcher;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/10
 */
@Slf4j
public class ZookeeperWatcherTest {


    public static void main(String[] args) throws Exception {
        String path = "/zk-book";
        CountDownLatch connectedSemaphore = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("10.0.45.193:2181",
                5000,
                new MyWatcher(connectedSemaphore));
        log.info("=============  zookeeper state is :" + zooKeeper.getState());
        connectedSemaphore.await();
        log.info("=============  zookeeper state is :" + zooKeeper.getState());


        zooKeeper.create(path,
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        byte[] data = zooKeeper.getData(path, true, null);
        log.info("=============  Get data :" + new String(data));
        log.info("=============  Set data.");
        Stat stat = zooKeeper.setData(path, "HelloWorld.".getBytes(), -1);
        log.info("=============  Set data`s stat: czxId： " + stat.getCzxid() + ", MzxId:" + stat.getMzxid() + ", version:" + stat.getVersion());


        data = zooKeeper.getData(path, true, null);
        log.info("=============  Get data :" + new String(data));
        log.info("=============  Set data.");
        Stat stat2 = zooKeeper.setData(path, "HelloWorld.".getBytes(), -1);
        log.info("=============  Set data`s stat2: czxId： " + stat2.getCzxid() + ", MzxId:" + stat2.getMzxid() + ", version:" + stat2.getVersion());


        zooKeeper.delete(path, stat2.getVersion());

        Thread.sleep(Integer.MAX_VALUE);
    }
}
