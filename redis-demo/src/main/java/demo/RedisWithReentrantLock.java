package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/11/30
 */
public class RedisWithReentrantLock {

    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();
    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
        this.lockers.set(new HashMap<>());
    }

    /**
     * RedisController 中设置锁
     * <p>
     * 1.不设置超时时间  存在的问题：
     * 设置了锁，释放锁之前，程序出现了异常退出，没有及时释放锁，则出现死锁
     * <p>
     * 2.设置超时时间  存在问题：
     * 设置了超时时间，但是超时时间到了之后，业务逻辑还没有执行完，
     * 另外的线程获取到了锁，
     * 2.1.起不到锁的作用了，临界区代码不能做到顺序执行
     * 2.2.两个线程同时获取到了锁，第一个线程的锁已经由于超时释放了，但是还没有执行释放锁的逻辑
     * 如果第一个线程再执行释放锁的逻辑，则就释放了第二个线程加的锁。
     *
     * @param key
     * @return
     */
    private boolean _lock(String key) {
        /**
         * NX： Only set the key if it does not already exist
         * EX： expire time units: EX = seconds;
         * time: 过期时间 5s
         */
        SetParams params = new SetParams();
        params.nx();
        params.ex(5);
        return this.jedis.set(key, "", params) != null;
    }

    /**
     * 释放锁
     * <p>
     * 为了解决问题 2.2
     * 可以将 set 指令的 value 值设置一个随机数，释放锁的时候匹配这个值是否一致，如果一致则是自己加的锁，否则则不是
     * 但是仍然解决不了问题 2.1
     *
     * @param key
     */
    private void _unLock(String key) {
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();

        if (refs != null) {
            return refs;
        }

        lockers.set(new HashMap<>());

        return refs;
    }


    /**
     * 可重入锁
     *
     * @param key
     * @return
     */
    public boolean Lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null) {
            refCnt++;
            refs.put(key, refCnt);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean Unlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null)
            return false;

        refCnt--;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unLock(key);
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyRedisConfig.class);
        Jedis jedis = (Jedis) context.getBean("jedis");

        System.out.println(""+Thread.currentThread());

        RedisWithReentrantLock lock = new RedisWithReentrantLock(jedis);
        System.out.println(lock.Lock("gbwxy"));
        System.out.println(lock.Lock("gbwxy"));
        System.out.println(lock.Lock("gbwxy"));
        System.out.println(lock.Unlock("gbwxy"));
        System.out.println(lock.Unlock("gbwxy"));
        System.out.println(lock.Unlock("gbwxy"));
        System.out.println(lock.Unlock("gbwxy"));

        Thread t1 = new Thread() {
            public void run() {
                System.out.println(""+Thread.currentThread());
            }
        };
        t1.start();


        Thread.sleep(Integer.MAX_VALUE);
    }
}
