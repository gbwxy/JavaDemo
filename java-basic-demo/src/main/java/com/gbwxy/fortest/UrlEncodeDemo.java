package com.gbwxy.fortest;


import org.apache.catalina.util.URLEncoder;

import java.nio.charset.Charset;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/1/8
 */
public class UrlEncodeDemo {


    public static void main(String[] args) {
        URLEncoder urlEncoder = new URLEncoder();

        String value = "1-avg(rate(node_cpu_seconds_total{mode=\\\"idle\\\", cluster=\\\"\\\", cluster_id=\\\"cce-kht4hb4f3jbq\\\"}[1m]))";

        String encode = urlEncoder.encode(value, Charset.defaultCharset());
        System.out.println(encode);
    }
}
