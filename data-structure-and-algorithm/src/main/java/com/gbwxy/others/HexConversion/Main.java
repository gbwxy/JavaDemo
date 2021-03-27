package com.gbwxy.others.HexConversion;

import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/26
 */
//
//        写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
//
//        输入描述:
//        输入一个十六进制的数值字符串。
//
//
//        输出描述:
//        输出该数值的十进制字符串。
//
//
//        输入例子1:
//        0xA
//
//        输出例子1:
//        10


public class Main {
//    char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'D', 'F'};


    /**
     * 自己写转换
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        List<Integer> result = new ArrayList<>();
//        while (scanner.hasNext()) {
//            int num = 0;
//
//            char[] chars = scanner.next().toCharArray();
//            int length = chars.length;
//
//            for (int ii = 2; ii < length; ii++) {
//                char c = chars[ii];
//                int n = 0;
//                switch (c) {
//                    case 'a':
//                    case 'A':
//                        n = 10;
//                        break;
//                    case 'b':
//                    case 'B':
//                        n = 11;
//                        break;
//                    case 'c':
//                    case 'C':
//                        n = 12;
//                        break;
//                    case 'd':
//                    case 'D':
//                        n = 13;
//                        break;
//                    case 'e':
//                    case 'E':
//                        n = 14;
//                        break;
//                    case 'f':
//                    case 'F':
//                        n = 15;
//                        break;
//                    default:
//                        n = (int) c - 48;
//
//                }
//                //Math.pow(3,4)=81
//                //Math.pow(81.0, 1.0/4)=3
//                int pow = (int) Math.pow(16, length - 1 - ii);
//                num += pow * n;
//            }
//            result.add(num);
//        }
//
//        result.forEach(num -> {
//            System.out.println(num);
//        });
//
//
//    }

    /**
     * Java库转换
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next().substring(2);
            System.out.println(Integer.parseInt(str, 16));
        }
    }

}
