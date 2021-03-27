package com.gbwxy.others;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FairDivision {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int totalNum = scanner.nextInt();
            int[] nums = new int[totalNum];
            int aiNum = scanner.nextInt();
            for (int idx = 0; idx < totalNum; idx++) {
                nums[idx] = scanner.nextInt();
            }

            for (int ii = 0; ii < totalNum; ii++) {

            }


        }


        HashSet set = new HashSet();
        HashMap map = new HashMap();

        scanner.close();
    }


    public static int absNum(int[] a, int[] b) {
        int result = 0;
        for (int ii = 0; ii < a.length; ii++) {
            for (int jj = 0; jj < b.length; jj++) {
                result += Math.abs(a[ii] - b[jj]);
            }
        }
        return result;
    }
}
