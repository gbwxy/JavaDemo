package demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/11/30
 */
public class RedisDelayingQueue<T> {


    static class TaskItem<T> {
        public String id;
        public T msg;

    }

    //获取泛型的类型
    private Type TaskType = new TypeReference<TaskItem<T>>() {
    }.getType();

    private Jedis jedis;
    private String queueKey;
    //private Type TaskType = this.getClass().getGenericSuperclass();

    public RedisDelayingQueue(String key, Jedis jedis) {
        this.queueKey = key;
        this.jedis = jedis;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        String s = JSON.toJSONString(task);
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
    }


    public void loop() {
        while (!Thread.interrupted()) {
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);           //  歇会儿继续
                } catch (InterruptedException ie) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) {        // 抢到了
                TaskItem<T> task = JSON.parseObject(s, TaskType);
                this.HandleMsg(task);
            }
        }
    }

    public void HandleMsg(TaskItem<T> task) {
        System.out.println("Task id is " + task.id + ", Task msg is " + task.msg);
    }


    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyRedisConfig.class);
        Jedis jedis = (Jedis) applicationContext.getBean("jedis");

        RedisDelayingQueue<User> queue = new RedisDelayingQueue("q-demo", jedis);

        Thread producer = new Thread("producer") {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    User user = new User("gbwxy" + i, i + 20);
                    queue.delay(user);
                }
            }
        };
        Thread producer02 = new Thread("producer02") {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    User user = new User("gbwxy" + i, i + 20);
                    queue.delay(user);
                }
            }
        };

        Thread producer03 = new Thread("producer") {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    User user = new User("gbwxy" + i, i + 20);
                    queue.delay(user);
                }
            }
        };


        new Thread("consumer01") {
            public void run() {
                queue.loop();
            }
        }.start();

        new Thread("consumer02") {
            public void run() {
                queue.loop();
            }
        };

        producer.start();
        Thread.sleep(1000);
        producer02.start();
        Thread.sleep(1000);
        producer03.start();

    }

    @Data
    static class User {
        public User(String name, int age) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.age = age;
        }

        private String id;
        private String name;
        private int age;
    }
}
