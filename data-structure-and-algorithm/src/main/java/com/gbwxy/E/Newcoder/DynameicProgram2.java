package com.gbwxy.E.Newcoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串s和一组单词dict，
 * 在s中添加空格将s变成一个句子，使得句子中的每一个单词都是dict中的单词
 * 返回所有可能的结果
 * 例如：给定的字符串s ="catsanddog",
 * dict =["cat", "cats", "and", "sand", "dog"].
 * <p>
 * 返回的结果为["cats and dog", "cat sand dog"].
 */

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/6/2
 */
public class DynameicProgram2 {

    public static ArrayList<String> wordBreak(String s, Set<String> dict) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        return getStu(s, dict, map);

    }

    private static ArrayList<String> getStu(String s, Set<String> dict, HashMap<String, ArrayList<String>> map) {

        if (map.containsKey(s))
            return map.get(s);

        ArrayList<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String subStr : dict) {
            if (s.startsWith(subStr)) {

                for (String str : getStu(s.substring(subStr.length()), dict, map)) {
                    res.add(subStr + (str == "" ? "" : " ") + str);
                }

            }
        }

        map.put(s, res);
        return res;

    }


}
