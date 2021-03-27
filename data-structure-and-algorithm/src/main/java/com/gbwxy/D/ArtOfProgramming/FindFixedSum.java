package com.gbwxy.D.ArtOfProgramming;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/15
 */
public class FindFixedSum {


    public static void Search(int n, int sum) {

        boolean[] k = new boolean[n];
        for (boolean b : k) {
            b = false;
        }
        Boolean flag = false;
        SumOfkNumber(n, sum, k, flag);
    }


    private static void SumOfkNumber(int n, int sum, boolean[] k, Boolean flag) {

        if (n == sum) {
            flag = true;
            for (int ii = 0; ii < k.length; ii++) {
                if (k[ii]) {
                    System.out.print(ii);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }


        k[n] = true;
        SumOfkNumber(n + 1, sum - n, k, flag);
        k[n] = false;
        SumOfkNumber(n + 1, sum, k, flag);

    }

}
