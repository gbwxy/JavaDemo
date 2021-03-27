package demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/12/3
 */
public class HyperLogLogDemo {


    private static final String key = "gbwxy";
    private static final String preFix = "user";

    private static AnnotationConfigApplicationContext applicationContext;
    private static Jedis jedis;


    public static void main(String[] args) {
//        applicationContext = new AnnotationConfigApplicationContext(demo.MyRedisConfig.class);
//        jedis = (Jedis) applicationContext.getBean("jedis");
//        DelKey(key);
//        AddData(key, 10000);

        for (int i = 100000; i < 10000000; i += 100000) {
            Experiment exp = new Experiment(i);
            exp.work();
            double estimate = exp.estimate();
            System.out.printf("%d  %.2f %.2f \n", i, estimate, Math.abs(estimate - i) / i);
        }

//        DelKey(key);
//        Close();
    }

    static void AddData(String key, int count) {

        for (int ii = 0; ii < count; ii++) {
            jedis.pfadd(key, preFix + ii);
            if (jedis.pfcount(key) != ii + 1) {
                int iii = ii + 1;
                System.out.println("ii = " + iii + ", count:" + jedis.pfcount(key));
            }

        }
        System.out.println(jedis.pfcount(key));

    }

    static void DelKey(String key) {
        jedis.del(key);

    }

    static void Close() {
        jedis.close();
    }


    static class BitKeeper {
        private int maxBits;

        public void random(long value) {
            //long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bits = lowZeros(value);
            if (bits > this.maxBits) {
                this.maxBits = bits;
            }
        }


        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }

    }


    static class Experiment {

        private int n;
        private int k;

        private BitKeeper[] keepers;

        public Experiment(int n) {
            this(n, 1024);
        }

        public Experiment(int n, int k) {
            this.n = n;
            this.k = k;
            this.keepers = new BitKeeper[k];
            for (int i = 0; i < this.k; i++) {
                this.keepers[i] = new BitKeeper();
            }
        }

        public void work() {
            for (int i = 0; i < this.n; i++) {
                long m = ThreadLocalRandom.current().nextLong(1L << 32);
                BitKeeper bitKeeper = keepers[(int) (((m & 0xfff0000) >> 16) % keepers.length)];
                bitKeeper.random(m);
            }
        }

        public double estimate() {
            double sumbitsInverse = 0;
            for (BitKeeper keeper : keepers) {
                sumbitsInverse += 1.0 / (float) keeper.maxBits;
            }
            double avgBits = (float) keepers.length / sumbitsInverse;
            return Math.pow(2, avgBits) * this.k;
        }


//        public void debug() {
//            System.out.printf("%d  %.2f  %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxBits);
//        }
    }


}
