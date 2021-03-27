package config;

import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/16
 */

@Configuration
public class ZookeeperConfig {
//
//    /**
//     * 获取 CuratorFramework
//     * 使用 curator 操作 zookeeper
//     *
//     * @return
//     */
//    @Bean
//    public ZooKeeper zooKeeper(ZookeeperProperties zkPro) {
//        try {
//            return new ZooKeeper(zkPro.getZkAddress(), zkPro.getSessionTimeoutMs(), new Watcher() {
//                @Override
//                public void process(WatchedEvent event) {
//
//                }
//            });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
}
