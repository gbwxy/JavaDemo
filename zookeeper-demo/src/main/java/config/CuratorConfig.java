package config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/16
 */

@Configuration
public class CuratorConfig {

    /**
     * 获取 CuratorFramework
     * 使用 curator 操作 zookeeper
     *
     * @return
     */
    @Bean
    public CuratorFramework curatorFramework(CuratorProperties curatorProperties) {
        //配置zookeeper连接的重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(curatorProperties.getBaseSleepTimeMs(), curatorProperties.getMaxRetries());

        //构建 CuratorFramework
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString(curatorProperties.getConnectString())
                        .sessionTimeoutMs(curatorProperties.getSessionTimeoutMs())
                        .connectionTimeoutMs(curatorProperties.getConnectionTimeoutMs())
                        .retryPolicy(retryPolicy)
                        .build();
        return curatorFramework;
    }
}
