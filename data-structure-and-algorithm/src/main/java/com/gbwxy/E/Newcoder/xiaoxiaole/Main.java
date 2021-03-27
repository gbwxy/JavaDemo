package com.gbwxy.E.Newcoder.xiaoxiaole;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/28
 */

//题目描述
//题目背景：
//
//        “曾经有两次消除的机会摆在我面前，我却没有珍惜……”牛牛回忆道。
//
//        牛牛正在玩一款全新的消消乐游戏。这款游戏的主体是由一列列的方块构成，牛牛的目标就是要尽量消除这些方块。
//
//        每次操作，牛牛可以选择某个高度 x，将所有高度大于等于 x 的那些列全部消除 x 个方块，随后方块会下落，以填补消除造成的空白。
//
//        牛牛这一局的发挥极佳，眼看就要破纪录了，却发现自己只剩下了两次消除机会。
//
//        为了不错失这千载难逢的机会，他决定写个程序来算出最优解。
//
//
//简明题意：
//
//        给定一个数组 nums，其中有 n 个非负整数。你的目的是进行两次操作，使得数组的元素之和最小。
//        每次操作形如：任选一个整数 x ，将数组中所有大于等于 x 的数减去 x 。
//
//示例1
//    输入
//        [2, 1, 3]
//    输出
//        0
//    说明
//        初始数组为 [2, 1, 3]。
//        先选择 x = 2，则所有大于等于 2 的元素减去 2 ，变成 [0, 1, 1]。
//        再选择 x = 1，则所有大于等于 1 的元素减去 1 ，变成 [0, 0, 0]。
//        所以数组元素之和的最小值为 0。
//        备注:
//        对于所有的测试数据，保证有1\leq n \leq 30001≤n≤3000 且 0\leq num_i \leq 2^{31}-10≤num
//        i
//        ​
//        ≤2
//        31
//        −1 。


public class Main {

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

    public static long minimumValueAfterDispel(int[] nums) {
        // write code here
        int N = nums.length;//长度
        Arrays.sort(nums);//排序
        long[] reduction = new long[N];//减去的数组，从该位置之后的数都要减去这个数所以是(n-i)*nums[i]
        for (int i = 0; i < N; i++) reduction[i] = (long) (N - i) * nums[i];//n*num[0],(n-1)*nums[1],
        int[] temp = new int[N];//
        long maxdelete = 0;//最大的减少
        // first deleted num in the list


        for (int i = 0; i < N; i++) {
            /**
             * i 是  第一次操作
             */
            //重复的数跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //获得减之后的数组--temp[]
            int l = 0, r = i, index = 0;
            while (l < i) temp[index++] = nums[l++];
            while (r < N) temp[index++] = nums[r++] - nums[i];
            //减之后的排序
            Arrays.sort(temp);
            for (int j = 0; j < N; j++) {
                /**
                 * j 是  第一次操作
                 */
                if (j > 0 && temp[j] == temp[j - 1]) continue;
                //取出两次最大减少的总数
                //      第一次要减去的值  reduction[i]
                //      第二次要减去的值  (long) (N - j) * (long) (temp[j])
                maxdelete = Math.max(maxdelete, reduction[i] + (long) (N - j) * (long) (temp[j]));
            }
        }


        //第二次筛选，同理
        for (int i = 0; i < N; i++) {
            int aindex = i + 1;
            //重复的跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < N; j++) {
                if (nums[j] == nums[j - 1] || nums[j] - nums[i] <= nums[i]) {
                    continue;
                }
                while (nums[aindex] < nums[j] - nums[i]) {
                    aindex++;
                }
                //比较与两次减少的总数谁大
                maxdelete = Math.max(maxdelete,
                        reduction[j] + (long) (j - aindex) * (long) (nums[j] - nums[i]) + (long) (aindex - i) * (long) (nums[i]));
            }
        }

        long sum = 0;
        for (int i = 0; i < N; i++)
            sum += nums[i];
        return sum - maxdelete;


    }


//    public static long minimumValueAfterDispel(int[] nums) {
//        // write code here
//        Arrays.sort(nums);
//        long sum = 0;
//        long max = 0;
//        for (int j = 0; j < nums.length; j++) {
//            sum = sum + nums[j];
//            int index = j;
//            int index2 = j;
//            int index3 = j;
//            for (int i = 0; i <= j; i++) {
//                while (index < nums.length && (long) nums[index] < (long) nums[i] + nums[j]) {
//                    index++;
//                }
//                while (index2 > 0 && nums[index2 - 1] >= nums[j] - nums[i]) {
//                    index2--;
//                }
//                while (index3 > i && nums[index3 - 1] >= nums[j] - nums[i]) {
//                    index3--;
//                }
//                long tmp = (nums.length - i) * (long) nums[i] + (nums.length - j + i - index2) * ((long) nums[j] - nums[i]);
//                long tmp2 = (nums.length - j) * (long) nums[j] + (nums.length - index + j - i) * (long) nums[i];
//                long tmp3 = (long) nums[i] * (index3 - i) + ((long) nums[j] - nums[i]) * (j - index3) + (long) nums[j] * (nums.length - j);
//                max = Math.max(max, tmp);
//                max = Math.max(max, tmp2);
//                max = Math.max(max, tmp3);
//            }
//        }
//        return sum - max;
//
//    }


//    public static long minimumValueAfterDispel(int[] nums) {
//
//        nums = sort(nums);
//        int currentNumIdx = getCurrentNumIdx(nums);
//        int zeroCount = 0;
//        for (int ii = 0; ii < nums.length; ii++) {
//            if (nums[ii] >= nums[currentNumIdx]) {
//                nums[ii] = nums[ii] - nums[currentNumIdx];
//            }
//            if (nums[ii] == 0) {
//                zeroCount++;
//            }
//        }
//
//        if (zeroCount == nums.length) {
//            //System.out.println(0);
//            return 0;
//        }
//
//        nums = sort(nums);
//        currentNumIdx = getCurrentNumIdx(nums);
//        zeroCount = 0;
//        for (int ii = 0; ii < nums.length; ii++) {
//            if (nums[ii] >= nums[currentNumIdx]) {
//                nums[ii] = nums[ii] - nums[currentNumIdx];
//            }
//            if (nums[ii] == 0) {
//                zeroCount++;
//            }
//        }
//
//        if (zeroCount == nums.length) {
//            //System.out.println(0);
//            return 0;
//        }
//
//        long result = 0;
//        for (int num : nums) {
//            result += num;
//        }
//        return result;
//
//    }


    private static int[] sort(int[] nums) {
        for (int ii = 0; ii < nums.length; ii++) {
            for (int jj = ii; jj > 0; jj--) {
                if (nums[jj - 1] < nums[jj]) {
                    int tmp = nums[jj - 1];
                    nums[jj - 1] = nums[jj];
                    nums[jj] = tmp;
                }
            }
        }
        return nums;
    }


    private static int getCurrentNumIdx(int[] nums) {

        int max = 0;
        int idx = 0;
        for (int ii = 0; ii < nums.length; ii++) {
            if (max <= nums[ii] * (ii + 1)) {
                max = nums[ii] * (ii + 1);
                idx = ii;
            }
        }
        return idx;
    }


}
