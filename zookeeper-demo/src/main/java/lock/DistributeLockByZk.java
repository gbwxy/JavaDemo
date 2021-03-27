package lock;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/16
 */
public class DistributeLockByZk {

    private String lockName;
    private String currentLock;
    private String waitLock;

    @Autowired
    private ZooKeeper zooKeeper;

    public boolean lock(String lockName) {
        this.lockName = lockName;
//        if(this.)
        return false;
    }
}
