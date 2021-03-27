package study.curator.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/25
 */
@Slf4j
public class TestingCluster_Sample {
    public static void main(String[] args) throws Exception {
        TestingCluster cluster = new TestingCluster(3);
        cluster.start();

        Thread.sleep(2000);

        TestingZooKeeperServer leader = null;
        for (TestingZooKeeperServer zs : cluster.getServers()) {
            log.info("====================  " + zs.getInstanceSpec().getServerId() + "-");
            log.info("====================  " + zs.getQuorumPeer().getServerState() + "-");
            log.info("====================  " + zs.getInstanceSpec().getDataDirectory().getAbsolutePath() + "-");

            if (zs.getQuorumPeer().getServerState().equals("leading")) {
                leader = zs;
            }

        }

        leader.kill();

        log.info("====================  After leader kill .");
        for (TestingZooKeeperServer zs : cluster.getServers()) {
            log.info("====================  " + zs.getInstanceSpec().getServerId() + "-");
            log.info("====================  " + zs.getQuorumPeer().getServerState() + "-");
            log.info("====================  " + zs.getInstanceSpec().getDataDirectory().getAbsolutePath() + "-");
        }

        cluster.stop();
    }
}
