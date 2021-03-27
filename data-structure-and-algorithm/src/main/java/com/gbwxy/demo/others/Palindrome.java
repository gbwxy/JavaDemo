package com.gbwxy.demo.others;

///**
// * 题目描述
// *      给定一个仅由小写字母组成的字符串。现在请找出一个位置，删掉那个字母之后，字符串变成回文。请放心总会有一个合法的解。如果给定的字符串已经是一个回文串，那么输出-1。
// * 输入描述:
// *      第一行包含T，测试数据的组数。后面跟有T行，每行包含一个字符串。
// * 输出描述:
// *      如果可以删去一个字母使它变成回文串，则输出任意一个满足条件的删去字母的位置（下标从0开始）。
// *          例如：
// *          bcc
// *          我们可以删掉位置0的b字符。
// * 示例1
//     * 输入
//     *      3
//     *      aaab
//     *      baa
//     *      aaa
//     * 输出
//     *      3
//     *      0
//     *      -1
// */

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int num = Integer.valueOf(scanner.nextLine());
            for (int ii = 0; ii < num; ii++) {
                String str = scanner.nextLine();
                if (isPalindromeIdx(str)) {
                    System.out.println(-1);
                } else {
                    for (int jj = 0; jj < str.length(); jj++) {
                        if (isPalindromeIdx(str.substring(0, jj) + str.substring(jj + 1))) {
                            System.out.println(jj);
                            break;
                        }
                    }
                }
            }
        }
    }


    public static boolean isPalindromeIdx(String str) {
        int len = str.length();
        for (int jj = 0; jj < len / 2; jj++) {
            if (str.charAt(jj) != str.charAt(len - 1 - jj)) {
                return false;
            }
        }
        return true;
    }
}
