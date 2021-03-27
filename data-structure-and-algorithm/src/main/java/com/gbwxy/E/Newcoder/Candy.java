package com.gbwxy.E.Newcoder;

import java.util.Arrays;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/6/2
 */
public class Candy {


    /**
     * 题目描述
     * 有N个小朋友站在一排，每个小朋友都有一个评分
     * 你现在要按以下的规则给孩子们分糖果：
     * 每个小朋友至少要分得一颗糖果
     * 分数高的小朋友要他比旁边得分低的小朋友分得的糖果多
     * 你最少要分发多少颗糖果？
     */
    /**
     * @param ratings int整型一维数组
     * @return int整型
     */
//    public static int candy(int[] ratings) {
//        // write code here
//
//        int len = ratings.length;
//        int[] nums = new int[len];
//        for (int ii = 0; ii < len; ii++) {
//            nums[ii] = 1;
//        }
//        for (int ii = 0; ii < len - 1; ii++) {
//            if (ratings[ii] < ratings[ii + 1] && nums[ii] >= nums[ii + 1]) {
//                nums[ii + 1] = nums[ii] + 1;
//            }
//            for (int jj = ii + 1; jj > 0; jj--) {
//                if (ratings[jj] < ratings[jj - 1] && nums[jj] >= nums[jj - 1]) {
//                    nums[jj - 1] = nums[jj] + 1;
//                }
//            }
//        }
//
//        return Arrays.stream(nums).sum();
//
//    }
//

    /**
     * 参考：
     *      1.Arrays.fill(count,1) 给数组赋初始值
     *      2.只需要两次循环，一次正向，一次反向，就可以了，不需要动态规划
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] count = new int[ratings.length];
        //每个孩子初始都有一个糖果
        Arrays.fill(count, 1);
        int sum = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                count[i] = count[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 1; i > 0; i--) {
            sum += count[i];
            if (ratings[i] < ratings[i - 1] && count[i] >= count[i - 1]) {
                count[i - 1] = count[i] + 1;
            }
        }
        sum += count[0];

        return sum;
    }

}
