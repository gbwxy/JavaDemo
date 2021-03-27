package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/18
 */
@Slf4j
public class Create_Session_Sample {

    private static String path = "/curator-base";

    public static void main(String[] args) throws Exception {


        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
//        retryPolicy = new RetryOneTime(3);
//        retryPolicy = new RetryNTimes(3, 3000);
//        retryPolicy = new RetryUntilElapsed(3000, 1000);

//
//        CuratorFramework client = CuratorFrameworkFactory.newClient("10.0.45.193:2181",
//                5000,
//                3000,
//                retryPolicy);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.0.45.193:2181")
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                //.namespace("base")//独立命名空间，该client的所有操作都在这个数据节点上进行
                .build();

        client.start();

        /**
         * zookeeper中规定，所有非叶子节点必须为持久化节点
         */
        client.create()
                .creatingParentsIfNeeded()//自动递归创建父节点-父节点是持久化的
                .withMode(CreateMode.EPHEMERAL)//创建一个临时节点
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path, "Hello World.".getBytes());

        Stat stat = new Stat();
        byte[] bytes = client.getData()
                .storingStatIn(stat)
                .forPath(path);
        log.info("=================  Get data : " + new String(bytes));
        log.info("=================  GetData stat :" + stat.getVersion() + "," + stat.getCzxid() + "," + stat.getMzxid());


        client.setData()
                .withVersion(stat.getVersion())
                .forPath(path, "Hello World. ChangeData.".getBytes());

        Stat stat2 = new Stat();
        bytes = client.getData()
                .storingStatIn(stat2)
                .forPath(path);
        log.info("================= Changed== Get data  : " + new String(bytes));
        log.info("================= Changed== GetData stat :" + stat2.getVersion() + "," + stat2.getCzxid() + "," + stat2.getMzxid());

        /**
         * BadVersion for /curator-base
         * 第二次使用过期的stat变量进行更新操作，抛出异常
         */
        try {
            client.setData()
                    .withVersion(stat.getVersion())
                    .forPath(path, "Hello World. ChangeData.".getBytes());
        } catch (Exception ex) {
            log.info("================= Error:" + ex.getMessage());
            ex.printStackTrace();
        }


        client.delete()
                .guaranteed()//强制删除
                .deletingChildrenIfNeeded()//递归删除其左右子节点
                .withVersion(-1)//强制指定版本进行删除
                .forPath(path);//


        Thread.sleep(Integer.MAX_VALUE);
    }
}
