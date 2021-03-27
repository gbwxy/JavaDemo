package config;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/16
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注入 zookeeper 的配置信息
 */
@Component
@ConfigurationProperties(prefix = "zookeeper")
@Data
public class ZookeeperProperties {
    private String zkAddress;
    private int sessionTimeoutMs;

}