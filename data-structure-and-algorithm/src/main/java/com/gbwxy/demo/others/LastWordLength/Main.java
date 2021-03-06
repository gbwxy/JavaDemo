package com.gbwxy.demo.others.LastWordLength;

import java.util.Scanner;


/**
 * 题目描述
 *      计算字符串最后一个单词的长度，单词以空格隔开。
 * 输入描述:
 *      一行字符串，非空，长度小于5000。
 * 输出描述:
 *      整数N，最后一个单词的长度。
 *
 * <example>
 *     输入
 *          hello world
 *     输出
 *          5
 * </example>

 */
public class Main {

    public static void main(String[] args) {

        String lastword = "";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            lastword = scanner.next();
        }
        System.out.println(lastword.length());
    }
}
