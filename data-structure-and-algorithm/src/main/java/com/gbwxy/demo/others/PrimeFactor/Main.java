package com.gbwxy.demo.others.PrimeFactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/27
 */

//题目描述
//        功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
//        最后一个数后面也要有空格
//
// 输入描述:
//        输入一个long型整数
//
// 输出描述:
//        按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
//
// 示例1
//     输入
//
//        180
//     输出
//
//        2 2 3 3 5


public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            long num = scanner.nextLong();
            List<Long> divs = new ArrayList<>();
//            divs = getdivs(num, divs);
            divs = getdivs2(num, divs);
            for (long div : divs) {
                System.out.printf("%d ", div);
            }
            System.out.println();
        }
    }

    /**
     * * 方法一：
     * *       从小到大，先判断是否为质数
     *
     * @param num
     * @param divs
     * @return
     */
    private static List<Long> getdivs(Long num, List<Long> divs) {
        if (num == 1) {
            return divs;
        }
        if (num == 2) {
            divs.add(Long.valueOf(2));
            return divs;
        }
        //如果本来就是质数
        if (isNumPrime(num)) {
            divs.add(num);
            return divs;
        }

        if (num % 2 == 0) {
            divs.add(Long.valueOf(2));
            return getdivs(num / 2, divs);
        } else {
            for (long ii = 2; ii < num; ii++) {
                if (isNumPrime(ii) && num % ii == 0) {
                    divs.add(ii);
                    return getdivs(num / ii, divs);
                }
            }
        }
        return divs;
    }

    /**
     * 方法二 ：
     * 递归，直接循环干，不需要判断num是否是质数
     *
     * @param num
     * @param divs
     * @return
     */
    public static List<Long> getdivs2(long num, List<Long> divs) {
        for (long i = 2; i <= num; i++) {
            if (num % i == 0) {
                divs.add(i);
                return getdivs2(num / i, divs);
            }
            if (i == num) {
                divs.add(i);
                return divs;
            }
        }
        return divs;
    }


    /**
     * 获取所有质数
     *
     * @param num
     * @return
     */
    private static List<Long> getPrimes(Long num) {
        List<Long> primes = new ArrayList<>();
        if (num == 1) {
            return null;
        } else if (num == 2) {
            primes.add(Long.valueOf(2));
            return primes;
        } else {
            for (long ii = 2; ii < num; ii++) {
                if (isNumPrime(num)) {
                    primes.add(num);
                }
            }
        }
        return primes;
    }


    /**
     * 是否为质数
     *
     * @param num
     * @return
     */
    private static boolean isNumPrime(Long num) {
        for (long ii = 2; ii < num; ii++) {
            if (num % ii == 0) {
                return false;
            }
        }
        return true;
    }
}
