package com.gbwxy.E.Newcoder;

import java.util.Iterator;
import java.util.Set;

//题目描述
//        给定一个字符串s和一组单词dict，判断s是否可以用空格分割成一个单词序列，
//        使得单词序列中所有的单词都是dict中的单词（序列可以包含一个或多个单词）。
//例如:
//        给定s=“leetcode”；
//        dict=["leet", "code"].
//        返回true，因为"leetcode"可以被分割成"leet code".

public class DynamicProgram {

    /**
     * @param s
     * @param dict
     * @return
     */
    public static boolean wordBreak(String s, Set<String> dict) {


        int[] idxs = new int[s.length() + 1];
        idxs[0] = 1;

        for (int ii = 1; ii <= s.length(); ii++) {
            for (int jj = ii - 1; jj >= 0; jj--) {
                if (idxs[jj] == 1 && dict.contains(s.substring(jj, ii))) {
                    // jj--(ii-1) 这个区间的单词在dict中
                    // 下一次截取从ii开始，所以设置idxs[ii] = 1
                    idxs[ii] = 1;
                    //只要改点匹配一个就可以
                    break;
                }

            }
        }
        //idxs[s.length()]之前都已经被截取并匹配
        return idxs[s.length()] == 1;
    }


}
