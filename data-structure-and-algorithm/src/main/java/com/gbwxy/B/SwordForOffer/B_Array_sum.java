package com.gbwxy.B.SwordForOffer.SwordForOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/22
 */
public class B_Array_sum {

    public static List<List<Double>> bufs = new ArrayList<>();


    /**
     * 找出数组中元素和为指定数值的所有组合
     * 例如：
     * 数值 11
     * 数组 [1.1,2.2,3.3,5.5,5.5,7.7,10,30]
     * 结果：
     * [2.2,3.3,5.5]
     * [5.5,5.5]
     * [3.3,7.7]
     * [1.1,2.2,7.7]
     *
     * @param args
     */
    public static void main(String[] args) {
        double[] arr = {1.1, 2.2, 3.3, 5.5, 5.5, 7.7, 10, 30};
        visit(arr, 0, 0, 11, new ArrayList<>());
    }

    public static void visit(double[] arr, int pos, double sum, int target, List<Double> buf) {
        if (pos >= arr.length) {
            return;
        }
        if (arr[pos] > 0 && arr[pos] + sum > target) {
            return;
        }
        buf.add(arr[pos]);
        if (sum + arr[pos] == target) {
            System.out.println(buf);
        }
        visit(arr, pos + 1, sum + arr[pos], target, buf);
        buf.remove(arr[pos]);
        visit(arr, pos + 1, sum, target, buf);
    }
}
