package com.gbwxy.others;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/28
 */
public class ScannerDemo {

    public static int[] getIntArray(String str) {
        //坑1  前后空格去掉
        String s = str.trim();
        String[] split = s.substring(1, s.length() - 1).split("\\,");
        int[] nums = new int[split.length];
        for (int ii = 0; ii < split.length; ii++) {
            //坑2  数字前后空格去掉
            int num = Integer.valueOf(split[ii].trim());
            nums[ii] = num;
        }
        return nums;
    }

    public static List<int[]> getIntArrayList(String str) {
        List<int[]> numList = new ArrayList<>();
        //坑1  前后空格去掉
        String[] s = str.trim().split("\\,");
        for (String sl : s) {
            String[] split = sl.substring(1, sl.length() - 1).split("\\,");
            int[] nums = new int[split.length];
            for (int ii = 0; ii < split.length; ii++) {
                //坑2  数字前后空格去掉
                int num = Integer.valueOf(split[ii].trim());
                nums[ii] = num;
            }
            numList.add(nums);
        }
        return numList;
    }


}
