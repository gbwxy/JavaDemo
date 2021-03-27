package com.gbwxy.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/6/9
 */

//
///**
// * 题目描述
//     * 在Z省，有若干个个城市坐落在一条直线上，从左到右依次标号1,2,3,…，
//     * 其中每个城市有一个火车站点，现今已经开放了n条火车路线，第i条火车路线是从第Yi个城市到第Xi个城市，
//     * 因为Z省地势奇特，标号小的城市地势较低，所以火车是从高往低开的，再通过神秘力量传送回高地，即Yi一定大于Xi，
//     * 它在沿途的所有城市都会停靠（显然不包括起点Yi，但是包括终点Xi），火车停靠就需要火车站台来运营维护。
//     * 火车站台随着运营线路的数量不同，其损耗速度、维护成本也各异，现在我们想知道所有站台中，所运营的线路数最大是多少。
// *
// * 输入描述:
//    * 第一行一个数n。（1≤n≤100000）
//    * 接下来n行每行两个数Xi和Yi，分别代表第i条火车路线的终点和起点。（1≤Xi<Yi≤1e5）
// * 输出描述:
//    * 共一行，一个非负整数，表示最大运营路线数。
// * 示例1
//    * 输入
//         * 4
//         * 4 7
//         * 2 6
//         * 2 5
//         * 1 2
//    * 输出
//        * 3
// */
public class ListToSum {

    /**
     * 此方法复杂度过高-超时
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.valueOf(scanner.nextLine());
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int ii = 0; ii < n; ii++) {
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
//            for (int jj = x; jj < y; jj++) {
//                int count = map.getOrDefault(jj, 0) == 0 ? 1 : map.get(jj) + 1;
//                map.put(jj, count);
//            }
//        }
//
//        int max = 0;
//        for (int value : map.values()) {
//            max = Math.max(max, value);
//        }
//
//        System.out.println(max);
//
//        scanner.close();
//    }


    /**
     * 把数组遍历问题 -- 优化为 --  求和问题
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        int[] nums = new int[100000 + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int ii = 0; ii < n; ii++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            nums[x]++;
            nums[y]--;
        }

        int max = 0;
        int[] total = new int[100000 + 1];
        for (int ii = 1; ii < nums.length; ii++) {
            total[ii] = total[ii - 1] + nums[ii];
            max = Math.max(total[ii], max);
        }

        System.out.println(max);

        scanner.close();
    }


}
