package study.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/22
 */
@Slf4j
public class Recipes_MasterSelect {
    static String master_path = "/curator_recipes_master_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("10.0.45.193:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    /**
     * 如果 同时 执行两个应用程序（两个本段程序同时执行）
     *      观察控制台输出，可以发现，当一个应用程序执行完Master逻辑（takeLeaderShip方法中的逻辑）后，
     *      另一个应用程序的takeLeaderShip方法才会被调用
     *      因此也证明，当一个应用实例成为Master后，其他应用实例会进入等待
     *      直到当前Master挂了，或者退出后才会开始选举新的 Master
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int count = 0;
        client.start();
        LeaderSelector selector = new LeaderSelector(client, master_path,
                new LeaderSelectorListenerAdapter() {
                    @Override
                    public void takeLeadership(CuratorFramework client) throws Exception {
                        log.info("成为Master角色");
                        Thread.sleep(3000);
                        log.info("完成Master操作，释放Master权限");
                    }
                });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);

    }
}
