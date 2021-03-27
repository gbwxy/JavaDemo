package demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/12/7
 */
public class FunnelRateLimiter {

    /**
     * 漏斗算法：先漏水，再灌水
     */
    static class Funnel {
        /**
         * 漏斗总容量
         */
        int capacity;
        /**
         * 漏水速率
         */
        float leakingRate;
        /**
         * 漏斗剩余容量
         */
        int leftQuota;
        /**
         * 记录上一次获取漏斗的时间
         */
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leakingTs = System.currentTimeMillis();
            this.leftQuota = capacity;
        }


        /**
         * 漏水
         */
        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            //计算上次获取漏斗，到目前，一共漏了多少水
            //deltaQuota 可以腾出的空间
            int deltaQuota = (int) (deltaTs * leakingRate);
            if (deltaQuota < 0) {
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            //腾出空间
            if (deltaQuota < 1) {
                return;
            }
            //剩余空间 = 原剩余空间 + 可以腾出来的空间
            this.leftQuota += deltaQuota;
            //更新上一次灌水时间
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        /**
         * 灌水
         *
         * @param quota
         * @return
         */
        boolean watering(int quota) {

            //灌水前先漏水
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }

    }


    private Map<String, Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }

        return funnel.watering(1);
    }

}
