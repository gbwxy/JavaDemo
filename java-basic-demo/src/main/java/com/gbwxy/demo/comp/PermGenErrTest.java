package com.gbwxy.demo.comp;

import java.util.Random;

public class PermGenErrTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            getRandomString(1000000).intern();
        }
        System.out.println("hello");
    }

    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            sb.append(str.charAt(num));
        }
        return sb.toString();
    }
}
