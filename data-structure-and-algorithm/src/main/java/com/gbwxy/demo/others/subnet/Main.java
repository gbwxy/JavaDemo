package com.gbwxy.demo.others.subnet;

//题目描述
//        子网掩码是用来判断任意两台计算机的IP地址是否属于同一子网络的根据。
//        子网掩码与IP地址结构相同，是32位二进制数，其中网络号部分全为“1”和主机号部分全为“0”。
//        利用子网掩码可以判断两台主机是否中同一子网中。若两台主机的IP地址分别与它们的子网掩码相“与”后的结果相同，则说明这两台主机在同一子网中。
//
// 示例：
//        I P 地址　 192.168.0.1
//        子网掩码　 255.255.255.0
//
//        转化为二进制进行运算：
//
//        I P 地址　11010000.10101000.00000000.00000001
//        子网掩码　11111111.11111111.11111111.00000000
//
//        AND运算
//        　　　　11000000.10101000.00000000.00000000
//
//        转化为十进制后为：
//        　　　　192.168.0.0
//
//
//
//        I P 地址　 192.168.0.254
//        子网掩码　 255.255.255.0
//
//
//        转化为二进制进行运算：
//
//        I P 地址　11010000.10101000.00000000.11111110
//        子网掩码　11111111.11111111.11111111.00000000
//
//        AND运算
//        　　　　　11000000.10101000.00000000.00000000
//
//        转化为十进制后为：
//        　　　　　192.168.0.0
//
//        通过以上对两台计算机IP地址与子网掩码的AND运算后，我们可以看到它运算结果是一样的。均为192.168.0.0，所以这二台计算机可视为是同一子网络。
//
///*
// * 功能: 判断两台计算机IP地址是同一子网络。
// * 输入参数：    String Mask: 子网掩码，格式：“255.255.255.0”；
// *               String ip1: 计算机1的IP地址，格式：“192.168.0.254”；
// *               String ip2: 计算机2的IP地址，格式：“192.168.0.1”；
// *
//
// * 返回值：      0：IP1与IP2属于同一子网络；     1：IP地址或子网掩码格式非法；    2：IP1与IP2不属于同一子网络
// */
//public int checkNetSegment(String mask, String ip1, String ip2)
//        {
//        /*在这里实现功能*/
//        return 0;
//        }
//
//
//
//        输入描述:
//        输入子网掩码、两个ip地址
//
//        输出描述:
//        得到计算结果
//
//        示例1
//        输入
//        255.255.255.0
//        192.168.224.256
//        192.168.10.4
//        输出
//        1

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String mask = scanner.next();
            String[] maskBin = new String[4];
            if (!isIpOk(mask, maskBin)) {
                System.out.println(1);
                continue;
            }
            String ip1 = scanner.next();
            String[] ip1Bin = new String[4];
            if (!isIpOk(ip1, ip1Bin)) {
                System.out.println(1);
                continue;
            }
            String ip2 = scanner.next();
            String[] ip2Bin = new String[4];
            if (!isIpOk(ip2, ip2Bin)) {
                System.out.println(1);
                continue;
            }
/**
 * 方法一：自己比较
 */
//            boolean isOk = true;
//            for (int ii = 0; ii < 4; ii++) {
//                int maskInt = Integer.valueOf(maskBin[ii]);
//                int ip1Int = Integer.valueOf(ip1Bin[ii]);
//                int ip2Int = Integer.valueOf(ip2Bin[ii]);
//                if (maskInt == 255 && ip1Int != ip2Int) {
//                    isOk = false;
//                    break;
//                }
//            }
//            if (isOk) {
//                System.out.println(0);
//            } else {
//                System.out.println(2);
//            }

            /**
             * 方法二  long & long
             */

            boolean isOk = true;
            for (int ii = 0; ii < 4; ii++) {
                if (compare(maskBin[ii], ip1Bin[ii]) != compare(maskBin[ii], ip2Bin[ii])) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                System.out.println(0);
            } else {
                System.out.println(2);
            }
        }
    }


    private static boolean isIpOk(String ip, String[] binStr) {
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            return false;
        }
        try {
            int idx = 0;
            for (String str : split) {
                Integer integer = Integer.valueOf(str);
                if (integer < 0 || integer > 255) {
                    return false;
                }
                binStr[idx] = str;
                idx++;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static long compare(String orgin, String str) {
        return Long.parseLong(orgin) & Long.parseLong(str);
    }
}