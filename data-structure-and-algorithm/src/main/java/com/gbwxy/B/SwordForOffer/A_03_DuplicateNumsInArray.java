package com.gbwxy.B.SwordForOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/4/8
 */
public class A_03_DuplicateNumsInArray {
    public static void main(String[] args) {

//        int[] arr = {2, 3, 1, 0, 2, 5, 3};
//        int num = func(arr);
//        System.out.println(num);

        int[] arr = {2, 3, 5, 4, 3, 2, 6, 7};
//        Integer num = funcTime(arr);
        Integer num = funcSpace(arr);
        System.out.println(num);
    }


    public static Integer func(int[] arr) {
        for (int ii = 0; ii < arr.length; ii++) {
            if (arr[ii] == ii) {
                continue;
            }
            int m = arr[ii];
            if (arr[ii] == arr[m]) {
                return arr[ii];
            } else {
                arr[ii] = arr[m];
                arr[m] = m;
            }
        }
        return null;
    }


    /**
     * 不改变原数组，时间优先算法
     *
     * @param arr
     * @return
     */
    public static Integer funcTime(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] nums = new int[arr.length + 1];
        for (int ii = 0; ii < arr.length; ii++) {
            int m = arr[ii];
            if (nums[m] == m) {
                return m;
            } else {
                nums[m] = m;
            }
        }
        return null;
    }


    /**
     * 不改变原数组，时间优先算法
     *
     * @param arr
     * @return
     */
    public static Integer funcSpace(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int len = arr.length;
        int start = 1;
        int end = len - 1;
        while (end >= start) {
            int mid = ((end - start) >> 1) + start;
            int count = countRange(arr, len, start, mid);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return null;
    }


    private static int countRange(int[] arr, int length, int start, int end) {
        if (arr == null) {
            return 0;
        }
        int count = 0;
        for (int ii = 0; ii < arr.length; ii++) {
            if (arr[ii] >= start && arr[ii] <= end) {
                count++;
            }
        }

        return count;
    }

}
