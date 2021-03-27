package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/11/30
 */
@Configuration
public class MyRedisConfig {


    private String host = "10.0.45.193";
    private int port = 6379;
    private String passwd = "cloudos";
    private String user = "root";

//    private String host = "r-bp1yqtm41eu4cmgwh4pd.redis.rds.aliyuncs.com";
//    private int port = 6379;
//    private String passwd = "Test6530!";
//    private String user = "roota";

    @Bean
    public Jedis jedis() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxWaitMillis(10000);
        //JedisPool pool = new JedisPool(jedisPoolConfig, host, port, 10000, user, passwd);
        JedisPool pool = new JedisPool(jedisPoolConfig, host, port, 10000, passwd);

        return pool.getResource();
    }

//    @Bean
//    public RedisTemplate redisTemplate() {
//
//    }

}
