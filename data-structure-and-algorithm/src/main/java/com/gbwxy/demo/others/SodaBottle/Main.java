package com.gbwxy.demo.others.SodaBottle;

import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/26
 */

//有这样一道智力题：
//         * 某商店规定：三个空汽水瓶可以换一瓶汽水。
//         * 小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”
//              * 答案是5瓶，
//         * 方法如下：
//              * 先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，
//              * 用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。
//              * 然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。
//              * 如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？

//输入描述:
//  输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），
//  表示小张手上的空汽水瓶数。n=0表示输入结束，你的程序不应当处理这一行。

//输出描述:
//  对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。


public class Main {


    /**
     * 通过数学分析，最后获得的饮料数是总空瓶数整除2 。
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            System.out.println(n / 2);
        }
    }


//
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        int idx = 0;
//        while (scanner.hasNext()) {
//
//            int n = scanner.nextInt();
//            if (n == 0) {
//                return;
//            }
//            if (idx >= 10) {
//                return;
//            }
//            idx++;
//
//            if (n < 1 || n > 100) {
//                continue;
//            } else {
//                int bottleCount = solution(n, 0);
//                System.out.println(bottleCount);
//            }
//        }
//    }
//
//
//    private static int solution(int n, int bottleCount) {
//        if (n == 1) {
//            return bottleCount;
//        } else if (n == 2) {
//            return bottleCount + 1;
//        } else {
////            //可换多少瓶
////            int div = n / 3;
////            //剩余的空瓶
////            int rema = n % 3;
////            //剩余的空瓶数
////            int total = div + rema;
////
////            //换了多少瓶
////            bottleCount = bottleCount + div;
//            return solution(n % 3 + n / 3, bottleCount + n / 3);
//        }
//    }


}
