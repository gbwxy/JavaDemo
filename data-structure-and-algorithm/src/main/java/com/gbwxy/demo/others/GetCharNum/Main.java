package com.gbwxy.demo.others.GetCharNum;

import java.util.Scanner;

/**
 * <P>题目描述</P>
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 * <P>输入描述:</P>
 * 第一行输入一个有字母和数字以及空格组成的字符串，第二行输入一个字符。
 * <P>输出描述:</P>
 * 输出输入字符串中含有该字符的个数。
 *
 * <example>
 * 输入
 * ABCDEF
 * A
 * <p>
 * 输出
 * 1
 * </example>
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            //坑1：不区分大小写
            String str = scanner.next().toLowerCase();
            String chr = scanner.next().toLowerCase();
            String[] split = str.split(chr);

            System.out.println(split.length - 1);

        }
    }


}
