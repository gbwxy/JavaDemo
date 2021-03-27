package com.gbwxy.D.ArtOfProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/1
 */
public class Main {


    public static void main(String[] args) {

//        String s = "abcdef";
//        String res = ConvertStrOne(s, 2);
//        System.out.println(res);
//        String res = ConvertStrTwo(s, 2);
//        System.out.println(res);


//        String s = "12277";
//        int result = GetIntFromStr(s);
//        System.out.println(result);

        String str = "abcd";
        Permutation.GetPermutationStrings(str.toCharArray(), 0, str.length() - 1);
    }

    /**
     * 循环套循环
     * 时间复杂度：O(M*N)
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean StrContainOne(String a, String b) {

        for (int ii = 0; ii < b.length(); ii++) {
//            if (a.contains(new String(new char[]{b.charAt(ii)}))) {
//                return true;
//            }
            int jj = 0;
            for (; (jj < a.length() && a.charAt(jj) != b.charAt(ii)); jj++)
                ;
            if (jj < a.length()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 先排序，再比较
     * 时间复杂度：O(M*logM)+O(M*logM)+O(M+N)
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean StrContainTwo(String a, String b) {
        char[] aC = a.toCharArray();
        Arrays.sort(aC);
        char[] bC = b.toCharArray();
        Arrays.sort(bC);

        for (int pa = 0, ii = 0; ii < bC.length; ii++) {
            while (pa < aC.length && aC[pa] < bC[ii]) {
                pa++;

            }
            if (pa >= aC.length && aC[pa] > bC[ii]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字母对应素数，乘积比较
     * 时间复杂度：O(m+n)的最好的情况为O(n)
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean StrContainThree(String a, String b) {
        int[] p = new int[]{
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101
        };
        int f = 1;
        char[] aC = a.toCharArray();
        char[] bC = b.toCharArray();
        for (int i = 0; i < a.length(); ++i) {
            int x = p[aC[i] - 'A'];
            if (f % x == f) {
                f *= x;
            }
        }
        for (int i = 0; i < b.length(); ++i) {
            int x = p[bC[i] - 'A'];
            if (f % x == f) {
                return false;
            }
        }
        return true;
    }

    /**
     * 采用HashTable的思想
     * 用一个int作为一个table
     * 用这个int的低26位分别标识A-Z的26个字母，0位-26位分别标识  A-Z  26个字母
     * 如果出现字母则对应的位上为 1，如果没有出现则为 0
     * 然后与b中的字母做比较，看看是否对应的位上是1，
     * 如果是则比较下一个，
     * 如果是0 则标识不存在该字母，直接返回false
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean StrContainFour(String a, String b) {
        int hash = 0;
        char[] aC = a.toCharArray();
        char[] bC = b.toCharArray();
        for (int i = 0; i < a.length(); ++i) {
            hash |= (1 << (aC[i] - 'A'));
        }
        for (int i = 0; i < b.length(); ++i) {
            if ((hash & (1 << (bC[i] - 'A'))) == 0) {
                return false;
            }
        }

        Hashtable tab = new Hashtable();
        tab.put(a, 1);

        HashMap map = new HashMap();
        map.put(a, 1);

        return true;

    }


    public static String ConvertStrOne(String s, int n) {
        char[] sc = s.toCharArray();
        char[] res = new char[sc.length];
        int idx = n;
        for (int ii = 0; ii < sc.length; ii++) {
            if (idx >= sc.length) {
                res[ii] = sc[idx - sc.length];
            } else {
                res[ii] = sc[idx];
            }
            idx++;
        }

        return new String(res);
    }


    public static String ConvertStrTwo(String s, int n) {
        char[] sc = s.toCharArray();
        sc = cov(sc, 0, n - 1);
        sc = cov(sc, n, sc.length - 1);
        sc = cov(sc, 0, sc.length - 1);
        return new String(sc);
    }

    private static char[] cov(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return chars;
    }


    public static int GetIntFromStr(String str) {
        char[] sc = str.toCharArray();
        int result = 0;
        for (int ii = 0; ii < sc.length; ii++) {
            int c = sc[ii] - '0';
            result = result * 10 + c;
        }

        return result;

    }


}


