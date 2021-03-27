package study.curator.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.File;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/25
 */
@Slf4j
public class TestingServer_Sample {

    static String path = "/zookeeper";

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer(2181, new File("/home/admin/zk_book"));

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(server.getConnectString())
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        log.info("===================  " + client.getChildren().forPath(path));


        Thread.sleep(1000);
        server.close();


    }

}
