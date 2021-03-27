package com.gbwxy.demo.others.StringSplit;


import java.util.Scanner;

/**
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 * 输出描述:
 * 输出到长度为8的新字符串数组
 *
 * <example>
 * 输入
 * abc
 * 123456789
 * 输出
 * abc00000
 * 12345678
 * 90000000
 * </example>
 */

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            //坑1:空字符串不处理
            if (isStringBlank(str)) {
                continue;
            }
            int length = str.length();

            int div = length / 8;
            if (div == 0) {
                System.out.println(get8Char(str));
            } else {
                int r = length % 8;
                for (int ii = 0; ii < div; ii++) {
                    //坑2:substring(begin,end)--begin包含，end不包含  [begin,end)
                    System.out.println(str.substring(8 * ii, 8 * (ii + 1)));
                }
                if (r > 0) {
                    //马虎：这里sub是从div*8开始的，不是length-div*8开始
                    str = str.substring(div * 8);
                    System.out.println(get8Char(str));
                }
            }
        }
    }

    private static String get8Char(String str) {
        int length = str.length();
        for (int ii = length; ii < 8; ii++) {
            str += "0";
        }
        return str;
    }


    private static boolean isStringBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
