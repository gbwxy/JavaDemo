package com.gbwxy.demo.others.ApproximateValue;


//题目描述
//        写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
//
// 输入描述:
//        输入一个正浮点数值
//
// 输出描述:
//        输出该数值的近似整数值
//
// 示例1
//    输入
//        5.5
//    输出
//        6

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            String str = scanner.next();
            //坑1 str.split(".")  为{} 空数组  需要用转义符  . 和 | 都需要转义符
            //String[] split = str.split(".");
            String[] split = str.split("\\.");
            if (split.length == 1) {
                System.out.println(str);
            } else {
                //坑2  substring(a,b) 包含a 不包含b
                if (Integer.valueOf(split[1].substring(0, 1)) >= 5) {
                    System.out.println(Integer.valueOf(split[0]) + 1);
                } else {
                    System.out.println(Integer.valueOf(split[0]));
                }
            }
        }
    }

}
