package com.gbwxy.others.genshuixue;


/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例：
 * 输入：["eat","tea","tan","ate","nat","bat"]
 * 输出：
 * [
 * ["ate","eat","tea"],
 * ["nat","tam"],
 * ["bat"]
 * ]
 **/

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/3/18
 */
public class HeterotopicWords {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("eat");
        list.add("tea");
        list.add("tan");
        list.add("ate");
        list.add("nat");
        list.add("bat");

        List<List<String>> fun = fun(list);
        for (List<String> sList : fun) {
            System.out.println(sList);
        }

    }

    public static List<List<String>> fun(List<String> list) {
        Map<Integer, List<String>> map = new HashMap<>();

        for (String str : list) {
            int hash = getHash(str);
            System.out.println("str = " + str + ", hash = " + hash);
            if (map.get(hash) == null) {
                List<String> strList = new ArrayList<>();
                strList.add(str);
                map.put(hash, strList);
            } else {
                List<String> strList = map.get(hash);
                strList.add(str);
            }
        }

        List<List<String>> result = map.values().stream().collect(Collectors.toList());
        return result;
    }


    public static int getHash(String str) {
        char[] chars = str.toCharArray();
        int hash = 0;
        for (int i = 0; i < chars.length; i++) {
            hash = hash + (chars[i] - 'a');
        }
        return hash;
    }

}
