package com.gbwxy.E.Newcoder.ArrayStatistics;


import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/5/28
 */


public class Main {

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            //坑1  前后空格去掉
//            String s = scanner.nextLine().trim();
//            List<int[]> nums = ScannerDemo.getIntArrayList(s);
//            long result = countLR(nums.get(0), nums.get(1));
//            System.out.println(result);
//        }

        int[] a = {};
        int[] b = {};
        System.out.println(countLR(a, b));
    }


    /**
     * 计算有多少对符合条件的数对
     *
     * @param a int整型一维数组 数组a
     * @param b int整型一维数组 数组b
     * @return int整型
     */
    public static int countLR(int[] a, int[] b) {

//        Map<Integer,Integer> map = new HashMap<>();
//        map.getOrDefault()
//

        long p[] = new long[a.length];
        p[0] = a[0];
        for (int ii = 1; ii < a.length; ii++) {
            p[ii] = p[ii - 1] + a[ii];
        }
        // write code here
        int count = 0;
        for (int l = 0; l <= a.length - 1; l++) {
            for (int r = l; r <= a.length - 1; r++) {
                long bSum = (long) b[l] + b[r];
                if ((p[r] - p[l] + a[l]) == bSum) {
                    count++;
                }
            }
        }
        return count;

//        // write code here
//        int count = 0;
//        for (int l = 0; l <= a.length - 1; l++) {
//            for (int r = l; r <= a.length - 1; r++) {
//                long aSum = 0;
//                long bSum = (long) b[l] + b[r];
//                for (int idx = l; idx <= r; idx++) {
//                    aSum += (long) a[idx];
//                }
//                if (aSum == bSum) {
//                    count++;
//                }
//            }
//        }
//        return count;
    }

    //自己和自己比较的，有先后顺序的 考虑是否可以用map暂存，减少循环次数
    public int countLROther(int[] a, int[] b) {
        int[] p = new int[a.length];
        for (int i = 1; i < a.length; i++)
            p[i] = p[i - 1] + a[i - 1];
        Map<Integer, Integer> map = new HashMap<>();
        int re = 0;
        for (int i = 0; i < a.length; i++) {
            int count = map.getOrDefault(b[i] + p[i], 0);
            map.put(b[i] + p[i], count + 1);
            re += map.getOrDefault(p[i] - b[i] + a[i], 0);
        }
        return re;
    }

}
