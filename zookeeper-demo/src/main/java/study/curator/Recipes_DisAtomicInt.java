package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/22
 */
@Slf4j
public class Recipes_DisAtomicInt {
    static String distAtomicClient_path = "/curator_disatomicint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();


    public static void main(String[] args) throws Exception {
        client.start();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, distAtomicClient_path, new RetryNTimes(3, 1000));
        AtomicValue<Integer> rc = atomicInteger.add(8);
        log.info("===============  Result :" + rc.succeeded() + " Value :" + rc.postValue());

    }
}
