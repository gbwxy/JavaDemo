package demo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/12/7
 */
public class SimpleRateLimiter {

    private Jedis jedis;

    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 这里 Redis 操作都是针对同一个 Key 的，使用 Pipeline 可以显著提升 Redis 存取效率
     * 缺点：
     *      要记录时间窗口内所有的行为记录，如果这个量很大，比如 限定 60s 内操作不得超过 100w 次，
     *      这种场景是不适合做这样的限流的，因为会消耗大量的存储空间。
     *
     * @param userId
     * @param actionKey
     * @param period
     * @param maxCount
     * @return
     */
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        System.out.println("The Key is " + key);
        System.out.println("Now time is " + nowTs);


        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        Response<Long> zcard = pipe.zcard(key);
        pipe.exec();
        pipe.close();

        boolean isAllow = zcard.get() < maxCount;


        if (isAllow) {
            pipe = jedis.pipelined();
            pipe.multi();
            pipe.zadd(key, nowTs, "" + nowTs);
            pipe.expire(key, period + 60);
            pipe.exec();
            pipe.close();
        }

        return isAllow;

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyRedisConfig.class);
        Jedis jedis = (Jedis) applicationContext.getBean("jedis");

        SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter(jedis);

        for (int i = 0; i < 20; i++) {
            boolean actionAllowed = simpleRateLimiter.isActionAllowed("gbwxy", "reply", 60, 5);
            System.out.println(actionAllowed);
        }

    }
}
