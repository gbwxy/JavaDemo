package demo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/12/7
 */
public class RedisDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyRedisConfig.class);
        Jedis jedis = (Jedis) applicationContext.getBean("jedis");


        String key = "demo:gbwxy";


//        for (int i = 0; i < 1000000; i++) {
//            jedis.setex(key + i, 360, "0");
//        }


        for (int i = 0; i < 1000000; i++) {
            jedis.del(key + i);
        }
    }
}
