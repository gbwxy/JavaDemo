package com.gbwxy.demo.others.HW;

import java.util.*;

public class HwTest2 {

    static int n = 0;
    static int m = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.valueOf(scanner.nextLine());
        String[] strs = new String[n];
        for (int ii = 0; ii < n; ii++) {
            String str = scanner.nextLine();
            strs[ii] = str;
        }
        if (n == 1) {
            System.out.println(1);
        }
        m = strs[0].length();
        int[][] nums = new int[n][m];
        int[][] count = new int[n][m];
        for (int ii = 0; ii < n; ii++) {
            char[] chars = strs[ii].toCharArray();
            for (int jj = 0; jj < m; jj++) {
                nums[ii][jj] = chars[jj] == '0' ? 0 : 1;
            }
        }

        int max = 0;
        for (int ii = 0; ii < n; ii++) {
            for (int jj = 0; jj < m; jj++) {
                count[ii][jj] = getMax(nums, ii, jj);
                max = Math.max(max, count[ii][jj]);
            }
        }

        System.out.println(max * max);
    }

    private static int getMax(int[][] nums, int hang, int lie) {
        int curr = nums[hang][lie];
        int hangIdx = 0;

        while (hang + hangIdx < n && lie + hangIdx < m) {

            for (int jj = hang; jj <= hang + hangIdx; jj++) {
                for (int kk = lie; kk < lie + hangIdx; kk++) {
                    if (curr != nums[jj][kk]) {
                        break;
                    }
                }
            }
            hangIdx++;
        }
        return hangIdx;
    }
}
