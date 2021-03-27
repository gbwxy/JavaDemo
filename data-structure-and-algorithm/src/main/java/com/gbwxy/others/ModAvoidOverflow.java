package com.gbwxy.others;

import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/6/9
 * <p>
 * 解题思路：
 */

///**
// * 题目描述：
// *    给定一个仅由小写字母x和y组成且长度不超过10^5的字符串，每次可以将字符串中的一个子串xy替换成字符串yyx
// *    那么至少要替换多少次才能让字符串中不存在子串xy？

// * 输入描述:
// *      输入给定的字符串。
// * 输出描述:
// *      输出最少替换次数对10^9+7取模后的结果。
// *
// *示例1
// *      输入
// *          xxy
// *      输出
// *          3
// *
// */


/**
 * 大数阶乘，大数的排列组合等，一般都要求将输出结果对1000000007取模（取余）
 * 为什么总是1000000007呢= =
 * <p>
 * 1. 1000000007是一个质数（素数），对质数取余能最大程度避免冲突～
 * 2. int32位的最大值为2147483647，所以对于int32位来说1000000007足够大
 * 3. int64位的最大值为2^63-1，对于1000000007来说它的平方不会在int64中溢出
 * 所以在大数相乘的时候，因为(a∗b)%c=((a%c)∗(b%c))%c，所以相乘时两边都对1000000007取模，
 * 再保存在int64里面不会溢出
 */
public class ModAvoidOverflow {
    public static void main(String[] args) {

        /**
         * 此方法超时
         */
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        long xcount = 0;
//        long total = 0;
//        for (char c : str.toCharArray()) {
//            if (c == 'x') {
//                xcount++;
//            } else {
//                long sum = 0;
//                for (int ii = 0; ii < xcount; xcount++) {
//                    sum = sum * 2;
//                }
//                total = total + sum - 1;
//            }
//        }
//
//        System.out.println(total % (1000000007));
//        scanner.close();


/**
 * 解题思路：
 * - 题目本质是将x移动到y的后面需要多少次
 * - 从后往前统计y的数量
 * - 假设第一次检测到x时，有2个y在后面，x移动需要两次
 * - 该x移动后y的个数会成倍增长变成2y，所以要将当前y的个数更新为2*y
 * - 依次统计最后得到需要移动的次数，即y的总数
 * - 每次求2的幂后就求模 防止溢出  (xcount *2)%100000007 = (xcount%100000007*2%100000007)%100000007
 *                                                    = (xcount%100000007*2)%100000007
 */

//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        long ycount = 0;
//        long total = 0;
//
//        for (int ii = str.length() - 1; ii >= 0; ii--) {
//            char c = str.charAt(ii);
//            if (c == 'y') {
//                ycount++;
//            } else {
//                total += ycount;
//                //total %= 1000000007;//这里不取余，最后输出的时候取余也可以
//                ycount *= 2;
//                ycount %= 1000000007;
//            }
//
//        }
//
//
//        System.out.println(total % 1000000007);
//        scanner.close();


        /**
         *解题思路：
         * 1.将“xy”变为“yyx”，本质就是将“x”向后移动，只有‘y’在增加，‘x’的数目从没变过。
         * 2. 每次遇到一个“y”,统计一下这个“y”前面的“x”的数目num，移动次数就是2^(num)-1.
         * 3.每次求2的幂后就求模 防止溢出  (xcount *2)%100000007 = (xcount%100000007*2%100000007)%100000007
         *                                                     = (xcount%100000007*2)%100000007
         */
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        long xcount = 1;
        long total = 0;
        for (char c : str.toCharArray()) {
            if (c == 'x') {
                xcount *= 2;
                xcount %= 1000000007;
            } else {
                total = total + xcount - 1;
            }
        }

        System.out.println(total % (1000000007));
        scanner.close();

    }
}
