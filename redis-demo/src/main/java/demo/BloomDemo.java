package demo;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/12/18
 */
public class BloomDemo {

    private String chars;

    {
        StringBuilder builder = new StringBuilder();
       StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            builder.append((char) ('a' + i));
        }
    }
}
