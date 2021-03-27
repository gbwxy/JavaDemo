package com.gbwxy.B.SwordForOffer;

import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/22
 */
public class A_10_1_Fibonacci {

    /**
     * 題目：求斐波那契数列的第 n 项
     * 写一个函数，输入 n，求斐波那契数列的第 n 项
     * 斐波那契数列定义：
     * f(1) = 0
     * f(2) = 1
     * f(n) = f(n-1) + f(n-2) (n>1)
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            //long fib1 = FibRecursion(n);
            long fib2 = FibLoop(n);
            //System.out.println(fib1);
            System.out.println(fib2);
        }
    }

    /**
     * 方法一：递归
     * 该方法会随着 n 的增加，计算量会急剧增大
     * 并且对于 n 特别大的情况，JVM 栈深度特别大的时候，会出现 StackOverflowError
     *
     * @param n
     * @return
     */
    public static long FibRecursion(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return FibRecursion(n - 1) + FibRecursion(n - 2);
    }


    /**
     * 方法二：循环
     * 将递归方法换成循环
     * 时间复杂度 O(n)
     *
     * @param n
     * @return
     */
    public static long FibLoop(int n) {
        int[] fibNum = {0, 1};
        if (n < 2) {
            return fibNum[n];
        }
        long fib_1 = 0;
        long fib_2 = 1;
        long fib_N = 0;
        for (int i = 2; i <= n; i++) {
            fib_N = fib_1 + fib_2;
            fib_1 = fib_2;
            fib_2 = fib_N;
        }
        return fib_N;
    }


}
