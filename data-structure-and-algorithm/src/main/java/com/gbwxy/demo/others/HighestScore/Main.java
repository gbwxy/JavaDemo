package com.gbwxy.demo.others.HighestScore;

import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/26
 */
public class Main {


//      老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。当然，老师有时候需要更新某位同学的成绩.
//
//      输入描述:
//        输入包括多组测试数据。
//        每组输入第一行是两个正整数N和M（0 < N <= 30000,0 < M < 5000）,分别代表学生的数目和操作的数目。
//        学生ID编号从1编到N。
//        第二行包含N个整数，代表这N个学生的初始成绩，其中第i个数代表ID为i的学生的成绩
//        接下来又M行，每一行有一个字符C（只取‘Q’或‘U’），和两个正整数A,B,当C为'Q'的时候, 表示这是一条询问操作，他询问ID从A到B（包括A,B）的学生当中，成绩最高的是多少
//        当C为‘U’的时候，表示这是一条更新操作，要求把ID为A的学生的成绩更改为B。
//
//    输出描述:
//    对于每一次询问操作，在一行里面输出最高成绩.
//
//    输入例子1:
//    5 7
//    1 2 3 4 5
//    Q 1 5
//    U 3 6
//    Q 3 4
//    Q 4 5
//    U 4 5
//    U 2 9
//    Q 1 5
//
//    输出例子1:
//     5
//     6
//     5
//     9


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            //坑1：学号是从1开始
            //初始分--从1到n
            int[] scores = new int[n + 1];
            for (int ii = 1; ii <= n; ii++) {
                scores[ii] = scanner.nextInt();
            }

            for (int ii = 0; ii < m; ii++) {
                String c = scanner.next();
                int A = scanner.nextInt();
                int B = scanner.nextInt();

                if (c.equals("Q")) {

                    int maxScore = 0;
                    //坑2：需要考虑A和B的大小
                    //坑3：只有Q的时候考虑A和B的大小，U的时候不需要交换
                    int tmp = A;
                    if (A > B) {
                        tmp = A;
                        A = B;
                        B = tmp;
                    }

                    for (int jj = A; jj <= B; jj++) {
                        if (scores[jj] > maxScore) {
                            maxScore = scores[jj];
                        }
                    }
                    System.out.println(maxScore);
                }
                if (c.equals("U")) {
                    scores[A] = B;
                }
            }
        }
    }


}
