package com.gbwxy.D.ArtOfProgramming;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/9/14
 */
public class Permutation {

//
//    public static String[] GetPermutationStrings(char[] str, String[] preRes, int start) {
//        if (start == 0 && str.length == 1) {
//            return new String[]{String.valueOf(str[0])};
//        } else if (start == 0) {
//            return GetPermutationStrings(str, new String[]{String.valueOf(str[0])}, start + 1);
//        } else {
//            String[] result = new String[preRes.length * 2];
//            for (int ii = 0; ii < preRes.length; ii++) {
//                result[ii] = preRes[ii] + String.valueOf(str[start]);
//                result[preRes.length + ii] = String.valueOf(str[start]) + preRes[ii];
//            }
//            if (start > str.length - 2) {
//                return result;
//            } else {
//                return GetPermutationStrings(str, result, start + 1);
//            }
//        }
//    }

    public static void GetPermutationStrings(char[] str, int from, int to) {
        if (to <= 1) {
            return;
        }
        if (from == to) {
            System.out.println(str);
        } else {
            for (int j = from; j <= to; j++) {
                char tmp = str[j];
                str[j] = str[from];
                str[from] = tmp;

                GetPermutationStrings(str, from + 1, to);

                tmp = str[j];
                str[j] = str[from];
                str[from] = tmp;
            }
        }


    }
}
