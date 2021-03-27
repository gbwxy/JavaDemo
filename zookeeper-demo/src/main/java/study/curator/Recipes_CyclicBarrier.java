package study.curator;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/22
 */
@Slf4j
public class Recipes_CyclicBarrier {

    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Thread(new Runner("One")));
        executor.submit(new Thread(new Runner("Two")));
        executor.submit(new Thread(new Runner("Three")));

        executor.shutdown();
    }

    static class Runner implements Runnable {
        private String name;

        public Runner(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            log.info("=================  Hello every, I am already. My Name is : " + name);
            try {
                Recipes_CyclicBarrier.barrier.await();
            } catch (Exception ex) {
                log.error("=============  error : " + ex.getMessage());
            }
            log.info("=================  Hello every, Running...  My Name is : " + name);
        }
    }
}
