package com.gbwxy.E.Newcoder.xiaoxiaole;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/28
 */

//        首先问题可以简化为，选择两个高度a和b，并且有 c=a+b，a<=b<=c
//        对于每个元素，如果n[i]>=c那么该高度最终会被减去c，如果该高度b<=n[i]<c 会被减去b，同理如果该高度a<=n[i]<b会被减去 a。
//
//        我们将数组从小到大排序，并画出删除区域，我们可以将消去的面积分为三部分计算，
//        一部分是以为a高，一部分是以b为高，第三部分是以c为高。
//        并且我们可以推算得到，三个高度至少有两个高度出现在数组中。
//        证明：如果三个高度都不出现在数组内，我们可以提高或者到离其最近的高度。
//              如果有两个高度不出现在数组内。
//                  1.a和b没有出现，可以让a和b增加减小相同的大小，使得宽大的矩形的高增大到距离其最近的数组元素。
//                  2.c和(a或b)没有出现，可以让二者同时增大直到一者的高度为数组出现元素。

public class myMain {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            //坑1  前后空格去掉
            String s = scanner.nextLine().trim();
            String[] split = s.substring(1, s.length() - 1).split("\\,");
            int[] nums = new int[split.length];
            for (int ii = 0; ii < split.length; ii++) {
                //坑2  数字前后空格去掉
                int num = Integer.valueOf(split[ii].trim());
                nums[ii] = num;
            }
            long result = minimumValueAfterDispel(nums);
            System.out.println(result);
        }
    }

    //大坑  可以减去任意整数 x
    public static long minimumValueAfterDispel(int[] nums) {

        int N = nums.length;//长度
        Arrays.sort(nums);//排序

        long deleNum = 0;

        /**
         * 以下注释掉在这个方法，时间复杂多太高，运行超时
         */
//        //两次分别减去a、b，a<=b
//        for (int a = 1; a < nums[N - 1]; a++) {
//            for (int b = a; b < nums[N - 1]; b++) {
//
//                int small = a;
//                int big = b;
//                List<Long> aList = new ArrayList<>();
//                List<Long> bList = new ArrayList<>();
//                List<Long> abList = new ArrayList<>();
//                Arrays.stream(nums).forEach(num -> {
//                    if (small <= num && num < big) {
//                        aList.add((long) small);
//                    }
//                    if (big <= num && num < small + big) {
//                        bList.add((long) big);
//                    }
//                    if (num >= small + big) {
//                        abList.add((long) small + big);
//                    }
//                });
//                deleNum = Math.max(deleNum,
//                        aList.stream().mapToLong((s) -> s).sum()
//                                + bList.stream().mapToLong((s) -> s).sum()
//                                + abList.stream().mapToLong((s) -> s).sum());
//
//            }
//        }


        /**
         * 分为四种情况：a,b,c都在数组中（这种情况可以包含在以下三种情况中）
         *              a,b在数组中
         *              a,c在数组中
         *              b,c在数组中
         */

        //  nums  ---------jj---------------ii------------idx---------------
        //        ---------a ---------------b ------------a+b---------------
        for (int ii = 0; ii < N; ii++) {
            int idx = ii;
            for (int jj = 0; jj < ii; jj++) {
                while (idx < N && nums[idx] < nums[jj] + nums[ii]) {
                    idx++;
                }
                deleNum = Math.max(deleNum,
                        (long) nums[jj] * (ii - jj) + (long) nums[ii] * (idx - ii) + (long) (nums[ii] + nums[jj]) * (N - idx));
            }
        }

        //  nums  ----idx-------------------------ii---------------jj---------
        //        ---(b-a)------------------------a ---------------b ---------
        for (int ii = 0; ii < N; ii++) {
            int idx = 0;
            for (int jj = ii; jj < N; jj++) {
                while (idx < ii && nums[idx] < nums[jj] - nums[ii]) {
                    idx++;
                }
                deleNum = Math.max(deleNum,
                        (long) nums[jj] * (N - jj) + (long) nums[ii] * (jj - ii) + (long) (nums[jj] - nums[ii]) * (ii - idx));
            }
        }


        //  nums  ---------ii----------------idx--------------jj--------------
        //        ---------a ---------------(b-a)-------------b --------------
        for (int ii = 0; ii < N; ii++) {
            int idx = ii;
            for (int jj = ii; jj < N; jj++) {
                while (idx < jj && nums[idx] < nums[jj] - nums[ii]) {
                    idx++;
                }
                deleNum = Math.max(deleNum,
                        (long) nums[ii] * (idx - ii) + (long) nums[jj] * (N - jj) + (long) (nums[jj] - nums[ii]) * (jj - idx));
            }
        }


        return Arrays.stream(nums).mapToLong((s) -> s).sum() - deleNum;
    }

}
