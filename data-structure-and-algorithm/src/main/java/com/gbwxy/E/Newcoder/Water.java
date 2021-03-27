package com.gbwxy.E.Newcoder;

import java.util.Arrays;

public class Water {

    /**
     * 题目描述
     * 有n个水桶，第i个水桶里面水的体积为Ai，你可以用1秒时间向一个桶里添加1体积的水。
     * 有q次询问，每次询问一个整数pi,你需要求出使其中pi个桶中水的体积相同所花费的最少时间。
     * 对于一次询问如果有多种方案，则采用使最终pi个桶中水的体积最小的方案。
     *
     * 示例1
     * 输入
     *      4,3,[1,2,3,4],[2,2,4]
     * 输出
     *      [1,0,5]
     * 说明
     * 第一次：花费一秒变为 2 2 3 4     *
     * 第二次：已经存在两个水的体积一样的桶     *
     * 第三次：花费五秒从2 2 3 4变为4 4 4 4
     */
    /**
     * @param n int整型 水桶的个数
     * @param q int整型 询问的次数
     * @param a int整型一维数组 n个水桶中初始水的体积
     * @param p int整型一维数组 每次询问的值
     * @return int整型一维数组
     */
    public static int[] solve(int n, int q, int[] a, int[] p) {
        // write code here
        int[] result = new int[q];
        Arrays.sort(a);

        int[] cha = new int[n];
        int zeroCount = 0;
        for (int jj = 1; jj < n; jj++) {
            cha[jj] = a[jj] - a[jj - 1];
            if (cha[jj] == 0) {
                zeroCount++;
            }
        }


        for (int ii = 0; ii < q; ii++) {
            int sumNum = p[ii];
            if (sumNum == 0 || sumNum == 1) {
                result[ii] = 0;
            } else {
                if (zeroCount >= sumNum) {
                    result[ii] = 0;
                } else {

                    int[] chahe = new int[n];
                    for (int jj = 1; jj < n; jj++) {
                        chahe[jj] = chahe[jj - 1] + cha[jj];
                    }
                    int min = Integer.MAX_VALUE;
                    int idx = sumNum - 1;
                    for (int jj = sumNum - 1; jj < n; jj++) {
                        int count = sumNum - 1;
                        int minin = 0;
                        while (count > 0) {
                            minin += (idx) * chahe[jj - (sumNum - 1) + count];
                            count--;
                        }
                        if (min > minin) {
                            idx = jj;
                            min = minin;
                        }
                    }
                    result[ii] = min;
                    for (int jj = idx; jj > idx - sumNum + 1; jj--) {
                        cha[jj] = 0;
                    }
                    zeroCount = sumNum;
                }
            }
        }

        return result;
    }

}
