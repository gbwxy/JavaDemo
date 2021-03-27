package study.curator.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

/**
 * 描述：
 *
 * @Author wangjun
 */
@Slf4j
public class EnsurePathDemo {
    static String path = "/zk_book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    /**
     * EnsurePath 采取静默节点创建方式
     * 其内部实现就是试图创建指定节点，
     * 如果节点以及存在，那么就不进行任何操作，也不对外抛出异常
     * 否则创建数据节点
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        client.start();
        client.usingNamespace("zk_book");

        EnsurePath ensurePath = new EnsurePath(path);
        ensurePath.ensure(client.getZookeeperClient());
        //如果存在也不抛出异常
        ensurePath.ensure(client.getZookeeperClient());

        EnsurePath ensurePath2 = client.newNamespaceAwareEnsurePath("/c1");
        ensurePath2.ensure(client.getZookeeperClient());
    }
}
